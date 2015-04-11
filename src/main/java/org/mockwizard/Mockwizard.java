package org.mockwizard;

import io.dropwizard.setup.Environment;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.listeners.InvocationListener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mockwizard library is highly inspired by Mockito. It adapts the Mockito
 * ideas from the client to the server by allowing server side mock creation,
 * verification and stubbing.
 */
public class Mockwizard {
    private static Map<String, MockDetails> mocks = new HashMap<String, MockDetails>();
    public static String baseUri;

    /**
     * Client site setup method.
     * <p/>
     * Needs to be called ones from each test class in order to specifiy the
     * port of the test service instance.
     *
     * @param port the port of the test service instance.
     */
    public static void setup(int port) {
        baseUri = "http://localhost:" + port;
    }

    /**
     * Server side setup method.
     * <p/>
     * Needs to be called in the <code>run</code> method of your application
     * class.
     *
     * @param environment the application's {@link Environment}
     */
    public static void init(Environment environment) {
        MockingResource mockingResource = new MockingResource();
        environment.jersey().register(mockingResource);
        VerificationResource verificationResource = new VerificationResource();
        environment.jersey().register(verificationResource);
    }

    /**
     * Starts stubbing for the given methodCall which has to be of the format
     * 'objectname.methodname'.
     *
     * @param methodCall method call to be stubbed
     * @return stubbing object
     */
    public static Stubbing when(String methodCall) {
        return new Stubbing(methodCall);
    }

    /**
     * Starts verification for the given methodCall which has to be of the
     * format 'objectname.methodname'.
     *
     * @param methodCall method call to be verified
     * @return result of the verfication
     */
    public static void verify(String methodCall) {
        String servicename = methodCall.split("\\.")[0];
        String methodname = methodCall.split("\\.")[1];
        Verification verification = new VerificationRequest(servicename, methodname).submit();
        if (verification.isFailed()) {
            throw new VerificationException(verification);
        }
    }

    /**
     * Creates a mock.
     *
     * @param aClass the class of the mock
     * @return the mock object
     */
    static <T> T mock(Class<T> aClass) {
        MockDetails mockDetails = new MockDetails();
        T mock = Mockito.mock(aClass, getMockSettings(mockDetails));
        mockDetails.setMock(mock);
        mocks.put(aClass.getSimpleName().toLowerCase(), mockDetails);
        return mock;
    }

    public static void stub(MethodCall methodCall1) throws Exception {
        MockDetails mockDetails = Mockwizard.get(methodCall1.getServicename());
        Object mock = mockDetails.getMock();

        List<Param> params = methodCall1.getParams();
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

        Method method = mock.getClass().getMethod(methodCall1.getMethodname(), parameterTypes);
        Mockito.when(method.invoke(mock, args)).thenReturn(methodCall1.getReturnValue());

        mockDetails.setStubbed(methodCall1.getMethodname());
    }

    private static MockSettings getMockSettings(MockDetails mockDetails) {
        MockSettings mockSettings = Mockito.withSettings();
        InvocationListener listener = new MethodInvocationRecorder(mockDetails);
        mockSettings.invocationListeners(listener);
        return mockSettings;
    }

    public static MockDetails get(String servicename) {
        return mocks.get(servicename);
    }

    public static void reset() {
        for (MockDetails mockDetails : mocks.values()) {
            mockDetails.reset();
        }
    }
}
