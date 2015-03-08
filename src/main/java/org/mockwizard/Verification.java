package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class Verification {
    private final Client client = new Client();
    public Verification(String servicename, String methodname) {
        MethodCall methodCall = new MethodCall(servicename, methodname);

        WebResource provisionResource = client.resource(Mockwizard.BASE_URI).path("verifications");
        provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(methodCall).post();
    }
}
