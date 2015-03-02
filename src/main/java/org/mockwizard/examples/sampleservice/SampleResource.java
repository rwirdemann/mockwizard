package org.mockwizard.examples.sampleservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/samples")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

    private Gateway partnerService;

    public SampleResource(Gateway partnerService) {
        this.partnerService = partnerService;
    }

    @GET
    @Path("/foo")
    public Response foo() {
        int result = partnerService.foo();
        return Response.ok(result).build();
    }

    @GET
    @Path("/foo/{s}")
    public Response foo(@PathParam("s") String s) {
        int result = partnerService.foo(s);
        return Response.ok(result).build();
    }

    @GET
    @Path("/fooInteger")
    public Response fooInteger() {
        int result = partnerService.foo(3);
        return Response.ok(result).build();
    }
}