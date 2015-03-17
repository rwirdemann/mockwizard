package org.mockwizard;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/verifications")
public class VerificationResource {
    @Context
    private UriInfo uriInfo;

    @POST
    public Response create(MethodCall methodCall) throws Exception {
        MockDetails mockDetails = Mockwizard.get(methodCall.getServicename());
        VerificationResult result = mockDetails.verify(methodCall.getMethodname());
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI createdURI = ub.path(String.valueOf(result.getUuid())).build();

        return Response.created(createdURI).build();
    }
}