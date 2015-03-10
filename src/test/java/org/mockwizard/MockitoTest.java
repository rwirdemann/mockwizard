package org.mockwizard;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockwizard.examples.orderservice.Order;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingService;

import java.lang.reflect.Method;
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

        Class[] parameterTypes = new Class[1];
        Object[] args = new Object[1];
        args[0] = new Order();
        parameterTypes[0] = Order.class;

        Method method = service.getClass().getMethod("clear", parameterTypes);
        ClearingService verify = Mockito.verify(service);
        method.invoke(verify, args);
    }

    @Test
    public void testEnhancer() throws Exception {
        ClearingService mock = Mockwizard.mock(ClearingService.class);
        mock.clear(new Order());
        mock.clear(new Order());
        Mockwizard.verifyLocal("clearingservice.clear");
    }
}
