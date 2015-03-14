package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class Verification {
    private final MethodCall methodCall;

    public Verification(String servicename, String methodname) {
        methodCall = new MethodCall(servicename, methodname);
    }
    
    public void request() {
        Client client = new Client();
        WebResource provisionResource = client.resource(Mockwizard.baseUri).path("verifications");
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(methodCall).post();
    }
}
