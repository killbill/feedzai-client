package org.killbill.clients.feedzai;

public class FeedzaiClientException extends Exception {

    public FeedzaiClientException() {
    }

    public FeedzaiClientException(ErrorResponse error) {
        super("Response code = " + error.getCode() + ", message = " + error.getMessage());
    }

    public FeedzaiClientException(String message) {
        super(message);
    }

    public FeedzaiClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeedzaiClientException(Throwable cause) {
        super(cause);
    }

    public FeedzaiClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
