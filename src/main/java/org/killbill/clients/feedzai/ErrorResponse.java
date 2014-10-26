package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    private final Integer code;
    private final String message;

    @JsonCreator
    public ErrorResponse(@JsonProperty("code") Integer code,
                         @JsonProperty("message") String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
