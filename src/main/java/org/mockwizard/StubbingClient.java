package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.orderservice.quoteservice.Stubbing;

import javax.ws.rs.core.MediaType;

public class StubbingClient {
    public static final String HOST = "http://localhost:9050";
    private Client client = new Client();
    private Stubbing stubbing;
    private String servicename;
    private String methodname;

    public StubbingClient when(String methodCall) {
        this.servicename = methodCall.split("\\.")[0];
        this.methodname = methodCall.split("\\.")[1];
        stubbing = new Stubbing(servicename, methodname);
        return this;
    }

    public void thenReturn(double v) {
        WebResource provisionResource = client.resource(HOST).path("stubbings");
        stubbing.setSymbol("TSLA");
        stubbing.setPrice(v);
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(stubbing).post();
    }
}
