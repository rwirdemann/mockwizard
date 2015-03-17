package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificationResult {

    @JsonProperty
    private String message;

    @JsonProperty
    private String uuid;

    @JsonProperty
    private String method;

    public VerificationResult(String message, String method) {
        this.message = message;
        this.method = method;
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
