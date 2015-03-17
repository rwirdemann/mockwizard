package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Verification {

    @JsonProperty
    private String message;

    @JsonProperty
    private String uuid;

    @JsonProperty
    private String method;

    public Verification(String message, String method) {
        this.message = message;
        this.method = method;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getMessage() {
        return message;
    }

    public String getMethod() {
        return method;
    }
}
