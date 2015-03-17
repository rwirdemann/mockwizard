package org.mockwizard;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MockDetails {

    class InvocationDetails {
        int count = 0;
        private boolean stubbed = false;

        boolean isStubbed() {
            return stubbed;
        }
    }

    private Map<String, InvocationDetails> invocations = new HashMap<String, InvocationDetails>();
    private Object mock;

    public void setStubbed(String methodName) {
        System.out.println("STUBBING methodName = " + methodName);
        if (invocations.containsKey(methodName)) {
            InvocationDetails invocationDetails = invocations.get(methodName);
            invocationDetails.stubbed = true;
        } else {
            invocations.put(methodName, new InvocationDetails());
        }
    }
    
    public boolean isStubbed(String methodName) {
        return invocations.containsKey(methodName) && invocations.get(methodName).stubbed;
    }

    public void record(Method method) {
        System.out.println("Recording method call: " + mock.getClass().getSimpleName() + "." + method.getName());
        if (invocations.containsKey(method.getName())) {
            InvocationDetails details = invocations.get(method.getName());
            details.count++;
        } else {
            InvocationDetails details = new InvocationDetails();
            details.count = 1;
            invocations.put(method.getName(), details);
        }
    }

    public Verification verify(String methodCall) {
        if (invocations.containsKey(methodCall)) {
            InvocationDetails invocationDetails = invocations.get(methodCall);
            if (invocationDetails.isStubbed()) {
                // der erste recordete aufruf stammt vom stubbing, zählt also nicht
                if (invocationDetails.count > 1) {
                    System.out.println("SUCCESS: method '" + methodCall + "' called " + invocationDetails.count + " times");
                } else {
                    throw new RuntimeException("FAILURE: method '" + methodCall + "' was not called");
                }
            } else {
                if (invocationDetails.count > 0) {
                    System.out.println("SUCCESS: method '" + methodCall + "' called " + invocationDetails.count + " times");
                } else {
                    throw new RuntimeException("FAILURE: method '" + methodCall + "' was not called");
                }
            }
        } else {
            return new Verification("Wanted but not invoked:", methodCall);
        }
        return new Verification("Wanted but not invoked:", methodCall);
    }

    public Object getMock() {
        return mock;
    }

    public <T> void setMock(T mock) {
        this.mock = mock;
    }
}
