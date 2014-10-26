package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelRequest {

    private final String label;

    @JsonCreator
    public LabelRequest(@JsonProperty("label") String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
