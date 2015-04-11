package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.net.httpserver.Authenticator;

import java.util.UUID;

public class Verification {

    @JsonProperty
    private String message;

    @JsonProperty
    private String uuid;

    @JsonProperty
    private String method;

    @JsonProperty
    private boolean failed = false;

    public Verification() {
    }

    public static Verification SUCCESS() {
        Verification verification = new Verification();
        verification.failed = false;
        verification.uuid = UUID.randomUUID().toString();
        return verification;
    }

    public Verification(String message, String method) {
        this.failed = true;
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

    @Override
    public String toString() {
        return message + "\n  " + method + "(...)";
    }

    public boolean isFailed() {
        return failed;
    }
}
