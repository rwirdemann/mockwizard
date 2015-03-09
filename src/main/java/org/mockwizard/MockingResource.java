package org.mockwizard;

import org.mockito.Mockito;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.List;

@Path("/mockings")
public class MockingResource {

    @POST
    public void create(MethodCall mocking) throws Exception {
        Object mock = Mockwizard.get(mocking.getServicename());

        List<Param> params = mocking.getParams();
        Class[] parameterTypes = new Class[params.size()];
        Object[] args = new Object[params.size()];
        for (int i = 0; i < params.size(); i++) {
            Param param = params.get(i);
            parameterTypes[i] = param.getaClass();
            if (param.getValue() == null) {
                args[i] = Mockito.any(param.getaClass());
            } else {
                args[i] = param.getValue();
            }
        }

        Method method = mock.getClass().getMethod(mocking.getMethodname(), parameterTypes);
        Object methodCall = method.invoke(mock, args);
        Mockito.when(methodCall).thenReturn(mocking.getReturnValue());
    }
}
