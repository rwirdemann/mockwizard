package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.setup.Environment;
import org.apache.commons.lang.StringUtils;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

public class Mockwizard {
    private static Map<String, Object> services = new HashMap<String, Object>();
    private final Client client = new Client();
    private final Mocking mocking;
    private static String baseURI;

    public Mockwizard(String servicename, String methodname) {
        mocking = new Mocking(servicename, methodname);
    }

    public static Mockwizard when(String methodCall) {
        String servicename = methodCall.split("\\.")[0];
        String methodname = methodCall.split("\\.")[1];
        return new Mockwizard(servicename, methodname);
    }

    public <T> void thenReturn(T v) {
        if (StringUtils.isBlank(baseURI)) {
            throw new RuntimeException("Make sure to call Mockwizard.setup(int port) in your test class");
        }
        WebResource provisionResource = client.resource(baseURI).path("mockings");
        mocking.setReturnValue(v);
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(mocking).post();
    }

    public Mockwizard with(String s) {
        mocking.addParam(s);
        return this;
    }

    public Mockwizard with(Integer i) {
        mocking.addParam(i);
        return this;
    }

    public Mockwizard with(Boolean b) {
        mocking.addParam(b);
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

    public static void setup(int port) {
       baseURI = "http://localhost:" + port;
    }
}
