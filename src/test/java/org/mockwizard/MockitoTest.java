package org.mockwizard;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockwizard.examples.orderservice.Order;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingService;

import java.util.List;

public class MockitoTest {

    @Test
    public void testVerify() throws Exception {

        List l = Mockito.mock(List.class);
        l.add("Hallo");
        Mockito.verify(l).add("Hallo");

        ClearingService service = Mockito.mock(ClearingService.class);
        service.clear(new Order());
        Mockito.verify(service).clear(Mockito.any(Order.class));
    }
}
