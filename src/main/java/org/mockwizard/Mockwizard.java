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
    private final MethodCall mocking;
    public static String BASE_URI;

    public Mockwizard(String servicename, String methodname) {
        mocking = new MethodCall(servicename, methodname);
    }

    public static Mockwizard when(String methodCall) {
        String servicename = methodCall.split("\\.")[0];
        String methodname = methodCall.split("\\.")[1];
        return new Mockwizard(servicename, methodname);
    }

    public <T> void thenReturn(T v) {
        if (StringUtils.isBlank(BASE_URI)) {
            throw new RuntimeException("Make sure to call Mockwizard.setup(int port) in your test class");
        }
        WebResource provisionResource = client.resource(BASE_URI).path("mockings");
        mocking.setReturnValue(v);
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(mocking).post();
    }

    public static Verification verify(String methodCall) {
        String servicename = methodCall.split("\\.")[0];
        String methodname = methodCall.split("\\.")[1];
        return new Verification(servicename, methodname);
    }

    public Mockwizard with(String s) {
        mocking.addParam(s);
        return this;
    }

    public Mockwizard with(Integer i) {
        mocking.addParam(i);
        return this;
    }

    public Mockwizard with(Double d) {
        mocking.addParam(d);
        return this;
    }

    public Mockwizard with(Boolean b) {
        mocking.addParam(b);
        return this;
    }

    public static void init(Environment environment) {
        MockingResource mockingResource = new MockingResource();
        environment.jersey().register(mockingResource);
        VerificationResource verificationResource = new VerificationResource();
        environment.jersey().register(verificationResource);
    }

    public static <T> T mock(Class<T> aClass) {
        T mock = Mockito.mock(aClass);
        services.put(aClass.getSimpleName().toLowerCase(), mock);
        return mock;
    }

    public static Object get(String servicename) {
        return services.get(servicename);
    }

    public static void setup(int port) {
        BASE_URI = "http://localhost:" + port;
    }

}
