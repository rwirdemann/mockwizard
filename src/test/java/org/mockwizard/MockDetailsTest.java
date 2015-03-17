package org.mockwizard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockDetailsTest {

    @Test
    public void testNonStubbedMethod() throws Exception {
        MockDetails mockDetails = new MockDetails();
        VerificationResult result = mockDetails.verify("verifyme");
        assertEquals("Wanted but not invoked:", result.getMessage());
        assertEquals("verifyme", result.getMethod());
    }
}
