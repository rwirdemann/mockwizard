package org.mockwizard;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockwizard.examples.orderservice.Order;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingService;

public class MockwizardTest {

    @Test
    public void testStubbing() throws Exception {
        ClearingService mock = Mockwizard.mock(ClearingService.class);
        Mockwizard.stub(MethodCall.service("clearingservice").method("clear").param(Order.class).returnValue(true));
        Assert.assertTrue(mock.clear(new Order()));
    }

    @Test
    public void testVerify() throws Exception {
        ClearingService mock = Mockwizard.mock(ClearingService.class);
        mock.clear(new Order());
        
        Mockwizard.verifyLocal("clearingservice.clear");
        
        Mockito.verify(mock).clear(Mockito.any(Order.class));
    }
}
