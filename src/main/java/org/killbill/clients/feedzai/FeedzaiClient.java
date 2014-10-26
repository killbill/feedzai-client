package org.killbill.clients.feedzai;

public interface FeedzaiClient {

    public PaymentResponse scorePayment(PaymentRequest request) throws FeedzaiClientException;
}
