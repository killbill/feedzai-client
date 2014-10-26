package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaymentResponse {

    private final String id;
    private final Integer score;
    private final String type;
    private final Boolean likelyFraud; // HACK_API : Undocumented field and it is returned in CamelCase, so we can't deserialize it...
    private final Double baseRisk;
    private final List<Explanation> explanation;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonCreator
    public PaymentResponse(@JsonProperty("id") String id,
                           @JsonProperty("score") Integer score,
                           @JsonProperty("type") String type,
                           @JsonProperty("likelyFraud") Boolean likelyFraud,
                           @JsonProperty("baseRisk") Double baseRisk,
                           @JsonProperty("explanation") List<Explanation> explanation) {
        this.id = id;
        this.score = score;
        this.type = type;
        this.likelyFraud = likelyFraud;
        this.baseRisk = baseRisk;
        this.explanation = explanation;
    }

    public Boolean getLikelyFraud() {
        return likelyFraud;
    }

    public String getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public String getType() {
        return type;
    }

    public Double getBaseRisk() {
        return baseRisk;
    }

    public List<Explanation> getExplanation() {
        return explanation;
    }


    public static class Explanation {
        private final String description;
        private final Double risk;
        private final Double riskFactor;
        private final Double weight; // HACK_API Undocumented field
        private final Integer confidence;
        private final List<Detail> details;

        @JsonCreator
        public Explanation(@JsonProperty("description") String description,
                           @JsonProperty("risk") Double risk,
                           @JsonProperty("riskFactor") Double riskFactor,
                           @JsonProperty("weight") Double weight,
                           @JsonProperty("confidence") Integer confidence,
                           @JsonProperty("details") List<Detail> details) {

            this.description = description;
            this.risk = risk;
            this.riskFactor = riskFactor;
            this.weight = weight;
            this.confidence = confidence;
            this.details = details;
        }

        public Double getWeight() {
            return weight;
        }

        public String getDescription() {
            return description;
        }

        public Double getRisk() {
            return risk;
        }

        public Double getRiskFactor() {
            return riskFactor;
        }

        public Integer getConfidence() {
            return confidence;
        }

        public List<Detail> getDetails() {
            return details;
        }
    }

    public static class Detail {

        private final String attribute;
        private final String value;
        private final String operator;
        private final String reference;

        @JsonCreator
        public Detail(@JsonProperty("attribute") String attribute,
                      @JsonProperty("value") String value,
                      @JsonProperty("operator") String operator,
                      @JsonProperty("reference") String reference) {
            this.attribute = attribute;
            this.value = value;
            this.operator = operator;
            this.reference = reference;
        }

        public String getAttribute() {
            return attribute;
        }

        public String getValue() {
            return value;
        }

        public String getOperator() {
            return operator;
        }

        public String getReference() {
            return reference;
        }
    }
}
