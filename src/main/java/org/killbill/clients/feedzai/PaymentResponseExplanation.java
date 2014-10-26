package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// HACK_API PaymentResponse is contained into an additional Explanation
public class PaymentResponseExplanation {

    private final PaymentResponse explanation;

    @JsonCreator
    public PaymentResponseExplanation(@JsonProperty("explanation") PaymentResponse explanation) {
        this.explanation = explanation;
    }

    public PaymentResponse getExplanation() {
        return explanation;
    }
}
