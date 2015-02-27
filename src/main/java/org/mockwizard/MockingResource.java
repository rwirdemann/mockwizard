package org.mockwizard;

import org.mockito.Mockito;
import org.orderservice.quoteservice.Mocking;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Path("/mockings")
public class MockingResource {

    private Map<String, MockableService> services = new HashMap<String, MockableService>();

    public void addMock(String name, MockableService service) {
        services.put(name, service);
    }

    @POST
    public void create(Mocking mocking) throws Exception {
        MockableService mockableService = services.get(mocking.getServicename());
        Method method = mockableService.getClass().getMethod(mocking.getMethodname(), String.class);
        Object methodCall = method.invoke(mockableService, mocking.getSymbol());
        Mockito.when(methodCall).thenReturn(mocking.getPrice());
    }
}
