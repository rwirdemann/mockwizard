package org.mockwizard;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockwizard.examples.orderservice.Order;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingService;

public class MockwizardTest {

    @Test
    public void testMock() throws Exception {
        ClearingService mock = Mockwizard.mock(ClearingService.class);
        
        MethodCall mocking = new MethodCall("clearingservice", "clear");
        mocking.addParam(Order.class);
        mocking.setReturnValue(true);
        Mockwizard.stub(mocking);
        
        //Mockito.when(mock.clear(Mockito.any(Order.class))).thenReturn(true);
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
