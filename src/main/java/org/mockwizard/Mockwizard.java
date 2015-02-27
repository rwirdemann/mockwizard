package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.setup.Environment;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

public class Mockwizard {
    public static final String HOST = "http://localhost:9050";

    private static Map<String, Object> services = new HashMap<String, Object>();
    private final Client client = new Client();
    private final Mocking mocking;

    public Mockwizard(String servicename, String methodname) {
        mocking = new Mocking(servicename, methodname);
    }

    public static Mockwizard when(String methodCall) {
        String servicename = methodCall.split("\\.")[0];
        String methodname = methodCall.split("\\.")[1];
        return new Mockwizard(servicename, methodname);
    }

    public void thenReturn(double v) {
        WebResource provisionResource = client.resource(HOST).path("mockings");
        mocking.setSymbol("TSLA");
        mocking.setPrice(v);
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(mocking).post();
    }

    public Mockwizard with(String s) {
        mocking.addParam(s);
        return this;
    }

    public static void init(Environment environment) {
        MockingResource mockingResource = new MockingResource();
        environment.jersey().register(mockingResource);
    }

    public static <T> T mock(Class<T> aClass) {
        T mock = Mockito.mock(aClass);
        services.put(aClass.getSimpleName().toLowerCase(), mock);
        return mock;
    }

    public static <T> Object get(String servicename) {
        return services.get(servicename);
    }
}
