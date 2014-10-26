package org.killbill.clients.feedzai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.collect.ImmutableMap;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Realm;
import com.ning.http.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DefaultFeedzaiClient implements FeedzaiClient {

    private final boolean DEBUG = Boolean.parseBoolean(System.getProperty("org.kill-bill.feedzai.debug", "false"));
    public static final int DEFAULT_HTTP_TIMEOUT_SEC = 10;

    private final Logger log = LoggerFactory.getLogger(DefaultFeedzaiClient.class);

    private static final String USER_AGENT = "KillBill-Feedzai-Client/1.0";

    private static final String SANDBOX_LOCATION = "https://sandbox.feedzai.com";
    private static final String PRODUCTION_LOCATION = "https://api.feedzai.com";
    private static final String ENDPOINT_BASE = "/v1";
    private static final String PAYMENTS_URI = ENDPOINT_BASE + "/payments";
    private static final String HISTORY_PAYMENTS_URI = ENDPOINT_BASE + "/history/payments";
    private static final String USERS_URI = ENDPOINT_BASE + "/users";
    private static final String ACTIONS_URI = ENDPOINT_BASE + "/actions";

    private static final String PAYMENTS_LABEL = "/label";
    private static final String USERS_STATUS = "/status";
    private final String location;

    private final AsyncHttpClient httpClient;
    private final ObjectMapper mapper;

    private final String apiKey;

    public DefaultFeedzaiClient(boolean useSandbox, String apiKey) {
        final AsyncHttpClientConfig.Builder cfg = new AsyncHttpClientConfig.Builder();
        cfg.setUserAgent(USER_AGENT);
        this.apiKey = apiKey;
        this.httpClient = new AsyncHttpClient(cfg.build());
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.location = useSandbox ? SANDBOX_LOCATION : PRODUCTION_LOCATION;
    }

    @Override
    public PaymentResponse scorePayment(PaymentRequest request) throws FeedzaiClientException {
        final PaymentResponseExplanation response =  doCall("POST", PAYMENTS_URI, request, ImmutableMap.<String, String>of(), DEFAULT_HTTP_TIMEOUT_SEC, PaymentResponseExplanation.class);
        return response.getExplanation();
    }


    public PreviousPaymentResponse getPreviousPayment(String paymentId) throws FeedzaiClientException {
        final PreviousPaymentResponse response =  doCall("GET", String.format("%s/%s", PAYMENTS_URI, paymentId), null, ImmutableMap.<String, String>of(), DEFAULT_HTTP_TIMEOUT_SEC, PreviousPaymentResponse.class);
        return response;
    }


    public void labelPreviousPayment(String paymentId, LabelRequest request) throws FeedzaiClientException {
        doCall("PUT", String.format("%s/%s%s", PAYMENTS_URI, paymentId, PAYMENTS_LABEL), request, ImmutableMap.<String, String>of(), DEFAULT_HTTP_TIMEOUT_SEC, Response.class);
    }

    public void sendHistoricalPayments(HistoricalPayments request) throws FeedzaiClientException {
        doCall("POST", HISTORY_PAYMENTS_URI, request, ImmutableMap.<String, String>of(), DEFAULT_HTTP_TIMEOUT_SEC, Response.class);
    }

    // HACK_API 405 Method Not Allowed => Allow: PUT
    public UserStatusResponse getUserStatus(String userId) throws FeedzaiClientException {
        return doCall("GET", String.format("%s/%s%s", USERS_URI, userId, USERS_STATUS), null, ImmutableMap.<String, String>of(), DEFAULT_HTTP_TIMEOUT_SEC, UserStatusResponse.class);
    }

    public void blockUser(String userId, StatusRequest request) throws FeedzaiClientException {
        doCall("PUT", String.format("%s/%s%s", USERS_URI, userId, USERS_STATUS), request, ImmutableMap.<String, String>of(), DEFAULT_HTTP_TIMEOUT_SEC, Response.class);
    }

    public void sendUserAction(UserActionRequest request) throws FeedzaiClientException {
        doCall("POST", ACTIONS_URI, request, ImmutableMap.<String, String>of(), DEFAULT_HTTP_TIMEOUT_SEC, Response.class);
    }






    private <T> T doCall(final String verb, final String uri, final Object body, final Map<String, String> options, final int timeoutSec, final Class<T> clazz) throws FeedzaiClientException  {
        final String url = getFeedzaiUrl(location, uri);
        final AsyncHttpClient.BoundRequestBuilder builder = getBuilderWithHeaderAndQuery(verb, url, apiKey, options);
        if (!"GET".equals(verb) && !"HEAD".equals(verb)) {
            if (body != null) {
                try {
                    final String bodyJson = mapper.writeValueAsString(body);
                    builder.setBody(bodyJson);
                } catch (JsonProcessingException e) {
                }
            } else {
                builder.setBody("{}");
            }
        }
        return executeAndWait(builder, timeoutSec, clazz);
    }

    private <T> T executeAndWait(final AsyncHttpClient.BoundRequestBuilder builder, final int timeoutSec, final Class<T> clazz) throws FeedzaiClientException {
        Response response;
        final ListenableFuture<Response> futureStatus;
        try {
            futureStatus = builder.execute(new AsyncCompletionHandler<Response>() {
                @Override
                public Response onCompleted(final Response response) throws Exception {
                    return response;
                }
            });
            response = futureStatus.get(timeoutSec, TimeUnit.SECONDS);
        } catch (final IOException e) {
            throw new FeedzaiClientException(e);
        } catch (final InterruptedException e) {
            throw new FeedzaiClientException(e);
        } catch (final ExecutionException e) {
            throw new FeedzaiClientException(e);
        } catch (final TimeoutException e) {
            throw new FeedzaiClientException(e);
        }

        if (response != null && response.getStatusCode() == 401) {
            throw new FeedzaiClientException("Unauthorized call");

        } else if (response != null && (response.getStatusCode() == 404 || response.getStatusCode() == 204)) {
            // Return empty list for KillBillObjects instead of null for convenience
            if (Iterable.class.isAssignableFrom(clazz)) {
                for (final Constructor constructor : clazz.getConstructors()) {
                    if (constructor.getParameterTypes().length == 0) {
                        try {
                            return (T) constructor.newInstance();
                        } catch (InstantiationException e) {
                            return null;
                        } catch (IllegalAccessException e) {
                            return null;
                        } catch (InvocationTargetException e) {
                            return null;
                        }
                    }
                }
                return null;
            } else {
                return null;
            }
        } else if (response != null && response.getStatusCode() >= 400) {
            final ErrorResponse errorResponse = deserializeResponse(response, ErrorResponse.class);
            log.warn("Error code=" + errorResponse.getCode() + ", message" + errorResponse.getMessage());
            throw new FeedzaiClientException(errorResponse);
        }

        // No deserialization required?
        if (Response.class.isAssignableFrom(clazz)) {
            return (T) response;
        }
        return deserializeResponse(response, clazz);
    }

    private <T> T deserializeResponse(final Response response, final Class<T> clazz) throws FeedzaiClientException {
        final T result;
        try {
            if (DEBUG) {
                final String content = response.getResponseBody();
                log.debug("Received: " + content);
                result = mapper.readValue(content, clazz);
            } else {
                InputStream in = null;
                try {
                    in = response.getResponseBodyAsStream();
                    result = mapper.readValue(in, clazz);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            log.warn("Failed to close http-client - provided InputStream: {}", e.getLocalizedMessage());
                        }
                    }
                }
            }
        } catch (final IOException e) {
            throw new FeedzaiClientException(e);
        }
        return result;
    }


    private AsyncHttpClient.BoundRequestBuilder getBuilderWithHeaderAndQuery(final String verb, final String url, final String username, final Map<String, String> options) {
        AsyncHttpClient.BoundRequestBuilder builder;

        if (verb.equals("GET")) {
            builder = httpClient.prepareGet(url);
        } else if (verb.equals("POST")) {
            builder = httpClient.preparePost(url);
        } else if (verb.equals("PUT")) {
            builder = httpClient.preparePut(url);
        } else if (verb.equals("DELETE")) {
            builder = httpClient.prepareDelete(url);
        } else if (verb.equals("HEAD")) {
            builder = httpClient.prepareHead(url);
        } else if (verb.equals("OPTIONS")) {
            builder = httpClient.prepareOptions(url);
        } else {
            throw new IllegalArgumentException("Unrecognized verb: " + verb);
        }

        if (username != null) {
            final Realm realm = new Realm.RealmBuilder().setPrincipal(username).build();
            builder.setRealm(realm);
        }

        builder.addHeader("Content-Type", "application/json; charset=utf-8");

        for (final String key : options.keySet()) {
            if (options.get(key) != null) {
                builder.addQueryParameter(key, options.get(key));
            }
        }
        return builder;
    }

    private String getFeedzaiUrl(final String location, final String uri) throws FeedzaiClientException {
        try {
            final URI u = new URI(uri);
            if (u.isAbsolute()) {
                return uri;
            } else {
                return String.format("%s%s", location, uri);
            }
        } catch (URISyntaxException e) {
            throw new FeedzaiClientException(e);
        }
    }

}
