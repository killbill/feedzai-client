package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStatusResponse {

    private final String status;
    private final String userId;

    @JsonCreator
    public UserStatusResponse(@JsonProperty("status") String status,
                              @JsonProperty("userId") String userId) {
        this.status = status;
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }
}
