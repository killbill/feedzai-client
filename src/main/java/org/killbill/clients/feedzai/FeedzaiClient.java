package org.killbill.clients.feedzai;

public interface FeedzaiClient {

    public PaymentResponse scorePayment(PaymentRequest request) throws FeedzaiClientException;

    public PreviousPaymentResponse getPreviousPayment(String paymentId) throws FeedzaiClientException;

    public void labelPreviousPayment(String paymentId, LabelRequest request) throws FeedzaiClientException;

    public void sendHistoricalPayments(HistoricalPayments request) throws FeedzaiClientException;

    public UserStatusResponse getUserStatus(String userId) throws FeedzaiClientException;

    public void blockUser(String userId, StatusRequest request) throws FeedzaiClientException;

    public void sendUserAction(UserActionRequest request) throws FeedzaiClientException;
}
