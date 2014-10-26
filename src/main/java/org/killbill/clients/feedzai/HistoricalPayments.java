package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HistoricalPayments {

    private final List<HistoricalPayment> payments;

    @JsonCreator
    public HistoricalPayments(@JsonProperty("payments") List<HistoricalPayment> payments) {
        this.payments = payments;
    }

    public List<HistoricalPayment> getPayments() {
        return payments;
    }


    public static class HistoricalPayment {
        private final PaymentRequestWithTimestamp payment;
        private final String label;

        @JsonCreator
        public HistoricalPayment(@JsonProperty("payment") PaymentRequestWithTimestamp payment,
                                  @JsonProperty("label") String label) {
            this.payment = payment;
            this.label = label;
        }

        public PaymentRequestWithTimestamp getPayment() {
            return payment;
        }

        public String getLabel() {
            return label;
        }
    }
}
