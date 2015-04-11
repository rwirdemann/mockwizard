package org.mockwizard;

import org.mockwizard.Verification;

public class VerificationException extends RuntimeException {

    private Verification verification;

    public VerificationException(Verification verification) {
        this.verification = verification;
    }

    @Override
    public String getMessage() {
        return this.verification.toString();
    }

    @Override
    public String toString() {
        return this.verification.toString();
    }
}
