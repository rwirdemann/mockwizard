package org.mockwizard;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.orderservice.quoteservice.Mocking;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/mockings")
public class MockingResource {

    @POST
    public void create(Mocking mocking) throws Exception {
        Object mock = Mockwizard.get(mocking.getServicename());

        List<Param> params = mocking.getParams();
        Class[] parameterTypes = new Class[params.size()];
        Object[] args = new Object[params.size()];
        for(int i = 0; i < params.size(); i++) {
            Param param = params.get(i);
            parameterTypes[i] = param.getaClass();
            args[i] = param.getValue();
        }

        Method method = mock.getClass().getMethod(mocking.getMethodname(), parameterTypes);
        Object methodCall = method.invoke(mock, args);
        Mockito.when(methodCall).thenReturn(mocking.getPrice());
    }
}
