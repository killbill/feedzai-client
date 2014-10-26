Feedzai client
==============

Java client library to integrate with [Feedzai Fraud Detection system] (https://www.feedzai.com/developers/rest-api).

In order to use the client library one needs to obtain a public API key to connect to the system. The `DefaultFeedzaiClient` takes as an input a flag to indicate whether to connect to the sandox or production system and also the API key.

Usage
=====

The [api] (https://github.com/killbill/feedzai-client/blob/master/src/main/java/org/killbill/clients/feedzai/FeedzaiClient.java) can be used as demonstrated in the [unit test] (https://github.com/killbill/feedzai-client/blob/master/src/test/java/org/killbill/clients/feedzai/TestDefaultFeedzaiClient.java) case, or in the snippet below:

```
final FeedzaiClient client = new DefaultFeedzaiClient(true, apiKey);
final String paymentId = "test_pay_" + random.nextLong();
final PaymentRequest request = new PaymentRequest("test_cli", 20, "72.21.91.19", paymentId);
final PaymentResponse result = client.scorePayment(request);
...
```




