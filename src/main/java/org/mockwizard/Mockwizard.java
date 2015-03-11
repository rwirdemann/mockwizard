package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.setup.Environment;
import org.apache.commons.lang.StringUtils;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.listeners.InvocationListener;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mockwizard {
    private static Map<String, MockDetails> services = new HashMap<String, MockDetails>();
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

    public static void verifyLocal(String methodCall) {
        String servicename = methodCall.split("\\.")[0];
        String methodname = methodCall.split("\\.")[1];

        MockDetails mockDetails = Mockwizard.get(servicename);
        mockDetails.verify(methodname);
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

    public Mockwizard with(Class c) {
        mocking.addParam(c);
        return this;
    }

    public static void init(Environment environment) {
        MockingResource mockingResource = new MockingResource();
        environment.jersey().register(mockingResource);
        VerificationResource verificationResource = new VerificationResource();
        environment.jersey().register(verificationResource);
    }

    public static <T> T mock(Class<T> aClass) {
        MockDetails mockDetails = new MockDetails();
        T mock = Mockito.mock(aClass, getMockSettings(mockDetails));
        mockDetails.setMock(mock);
        services.put(aClass.getSimpleName().toLowerCase(), mockDetails);
        return mock;
    }

    public static void stub(MethodCall mocking) throws Exception {
        MockDetails mockDetails = Mockwizard.get(mocking.getServicename());
        Object mock = mockDetails.getMock();

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

        mockDetails.setStubbed(mocking.getMethodname());
    }

    private static MockSettings getMockSettings(MockDetails mockDetails) {
        MockSettings mockSettings = Mockito.withSettings();
        InvocationListener listener = new MethodInvocationRecorder(mockDetails);
        mockSettings.invocationListeners(listener);
        return mockSettings;
    }

    public static MockDetails get(String servicename) {
        return services.get(servicename);
    }

    public static void setup(int port) {
        BASE_URI = "http://localhost:" + port;
    }


}
