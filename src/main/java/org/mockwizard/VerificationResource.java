package org.mockwizard;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/verifications")
public class VerificationResource {
    private Map<String, VerificationResult> verificationResults = new HashMap<String, VerificationResult>();
    
    @Context
    private UriInfo uriInfo;

    @POST
    public Response create(MethodCall methodCall) throws Exception {
        MockDetails mockDetails = Mockwizard.get(methodCall.getServicename());
        VerificationResult result = mockDetails.verify(methodCall.getMethodname());
        verificationResults.put(result.getUuid(), result);
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI resultURI = ub.path(String.valueOf(result.getUuid())).build();
        return Response.created(resultURI).build();
    }

    @GET
    @Path("{uuid}")
    public Response get(@PathParam("uuid") String uuid) {
        if (verificationResults.containsKey(uuid)) {
            return Response.ok(verificationResults.get(uuid)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}