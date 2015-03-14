package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.MediaType;

public class Stubbing {
    private final Client client = new Client();
    private final MethodCall mocking;

    public Stubbing(String methodCall) {
        String servicename = methodCall.split("\\.")[0];
        String methodname = methodCall.split("\\.")[1];
        mocking = new MethodCall(servicename, methodname);
    }

    public <T> void thenReturn(T v) {
        if (StringUtils.isBlank(Mockwizard.baseUri)) {
            throw new RuntimeException("Make sure to call Mockwizard.setup(int port) in your test class");
        }
        WebResource provisionResource = client.resource(Mockwizard.baseUri ).path("mockings");
        mocking.setReturnValue(v);
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(mocking).post();
    }

    public Stubbing with(String s) {
        mocking.addParam(s);
        return this;
    }

    public Stubbing with(Integer i) {
        mocking.addParam(i);
        return this;
    }

    public Stubbing with(Double d) {
        mocking.addParam(d);
        return this;
    }

    public Stubbing with(Boolean b) {
        mocking.addParam(b);
        return this;
    }

    public Stubbing with(Class c) {
        mocking.addParam(c);
        return this;
    }
}
