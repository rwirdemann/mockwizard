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
    public void create(Mocking stubbing) throws Exception {
        MockableService mockableService = services.get(stubbing.getServicename());
        Method method = mockableService.getClass().getMethod(stubbing.getMethodname(), String.class);
        Object methodCall = method.invoke(mockableService, stubbing.getSymbol());
        Mockito.when(methodCall).thenReturn(stubbing.getPrice());
    }
}
