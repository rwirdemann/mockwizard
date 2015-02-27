package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.orderservice.quoteservice.Mocking;

import javax.ws.rs.core.MediaType;

public class Mockwizard {
    public static final String HOST = "http://localhost:9050";
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
}
