package org.mockwizard;

import org.mockito.Mockito;
import org.mockwizard.examples.orderservice.Order;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.lang.reflect.Method;

@Path("/verifications")
public class VerificationResource {

    @POST
    public void create(MethodCall methodCall) throws Exception {
        Class[] parameterTypes = new Class[1];
        Object[] args = new Object[1];
        args[0] = new Order();
        parameterTypes[0] = args[0].getClass();

        
        Mockwizard.verifyLocal(methodCall.getServicename() + "." + methodCall.getMethodname());

        //Mockito.verify(service).clear(Mockito.any(Order.class));

//        Object mock = Mockwizard.get(methodCall.getServicename());
//        Method method = mock.getClass().getMethod(methodCall.getMethodname(), parameterTypes);
//        method.invoke(Mockito.verify(mock), args);
    }
}