package org.mockwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.net.URI;

public class VerificationRequest {
    private final MethodCall methodCall;

    public VerificationRequest(String servicename, String methodname) {
        methodCall = new MethodCall(servicename, methodname);
    }
    
    public Verification submit() {
        Client client = new Client();
        WebResource provisionResource = client.resource(Mockwizard.baseUri).path("verifications");
        ClientResponse postResponse = provisionResource.type(MediaType.APPLICATION_JSON_TYPE).entity(methodCall).post(ClientResponse.class);
        URI location = postResponse.getLocation();
        ClientResponse result = client.resource(location).get(ClientResponse.class);
        return result.getEntity(Verification.class);
    }
}
