package org.mockwizard;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/verifications")
@Produces(MediaType.APPLICATION_JSON)
public class VerificationResource {
    private Map<String, Verification> verificationResults = new HashMap<String, Verification>();
    
    @Context
    private UriInfo uriInfo;

    @POST
    public Response create(MethodCall methodCall) throws Exception {
        MockDetails mockDetails = Mockwizard.get(methodCall.getServicename());
        Verification result = mockDetails.verify(methodCall.getMethodname());
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