package org.mockwizard;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/verifications")
public class VerificationResource {

    @POST
    public void create(MethodCall methodCall) throws Exception {
        MockDetails mockDetails = Mockwizard.get(methodCall.getServicename());
        mockDetails.verify(methodCall.getMethodname());
    }
}