package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PreviousPaymentResponse {

    private final String label;
    private final PaymentRequest payment;
    private final PaymentResponse score;

    @JsonCreator
    public PreviousPaymentResponse(@JsonProperty("label") String label,
                                   @JsonProperty("payment") PaymentRequest payment,
                                   @JsonProperty("score") PaymentResponse score) {
        this.label = label;
        this.payment = payment;
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public PaymentRequest getPayment() {
        return payment;
    }

    public PaymentResponse getScore() {
        return score;
    }
}
