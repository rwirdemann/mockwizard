package org.orderservice;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.orderservice.quoteservice.QuoteServiceProvision;

import javax.ws.rs.core.MediaType;

public class ValueProvider {
    public static final String HOST = "http://localhost:9050";
    private Client client = new Client();

    public void thenReturn(double v) {
        WebResource provisionResource = client.resource(HOST).path("quoteserviceprovisions");
        QuoteServiceProvision qsp = new QuoteServiceProvision("TSLA", v);
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(qsp).post();

    }
}
