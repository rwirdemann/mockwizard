package org.mockwizard.examples.sampleservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/samples")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

    private PartnerService partnerService;

    public SampleResource(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GET
    @Path("/foo")
    public Response foo() {
        int result = partnerService.foo();
        return Response.ok(result).build();
    }
}
