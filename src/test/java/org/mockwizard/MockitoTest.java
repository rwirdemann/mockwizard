package org.mockwizard;

import org.junit.Test;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;
import org.mockwizard.examples.orderservice.Order;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingService;

import java.lang.reflect.Method;
import java.util.List;

public class MockitoTest {

    @Test
    public void testMock() throws Exception {
        ClearingService mock = Mockito.mock(ClearingService.class, getMockSettings());
        Mockito.when(mock.clear(Mockito.any(Order.class))).thenReturn(true);
        mock.clear(new Order());
    }

    private MockSettings getMockSettings() {
        MockSettings mockSettings = Mockito.withSettings();
        InvocationListener listener = new InvocationListener() {
            @Override
            public void reportInvocation(MethodInvocationReport methodInvocationReport) {
                System.out.println("methodInvocationReport = " + methodInvocationReport.getInvocation());
            }
        };
        mockSettings.invocationListeners(listener);
        return mockSettings;
    }

    @Test
    public void testVerify() throws Exception {

        List l = Mockito.mock(List.class, getMockSettings());
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
}
