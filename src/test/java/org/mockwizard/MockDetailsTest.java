package org.mockwizard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MockDetailsTest {

    @Test
    public void shouldAssignUUID() throws Exception {
        MockDetails mockDetails = new MockDetails();
        Verification result = mockDetails.verify("verifyme");
        assertNotNull(result.getUuid());
    }

    @Test
    public void testNonStubbedMethod() throws Exception {
        MockDetails mockDetails = new MockDetails();
        Verification result = mockDetails.verify("verifyme");
        assertEquals("Wanted but not invoked:", result.getMessage());
        assertEquals("verifyme", result.getMethod());
    }
}
