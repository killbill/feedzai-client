package org.killbill.clients.feedzai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserActionRequest {

    private final String userId;
    private final String ip;
    private final String action;
    private final String itemId;
    private final Long timestamp;
    private final String target;

    @JsonCreator
    public UserActionRequest(@JsonProperty("userId") String userId,
                             @JsonProperty("ip") String ip,
                             @JsonProperty("action") String action,
                             @JsonProperty("itemId") String itemId,
                             @JsonProperty("timestamp") Long timestamp,
                             @JsonProperty("target") String target) {

        this.userId = userId;
        this.ip = ip;
        this.action = action;
        this.itemId = itemId;
        this.timestamp = timestamp;
        this.target = target;
    }

    public String getUserId() {
        return userId;
    }

    public String getIp() {
        return ip;
    }

    public String getAction() {
        return action;
    }

    public String getItemId() {
        return itemId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getTarget() {
        return target;
    }
}
