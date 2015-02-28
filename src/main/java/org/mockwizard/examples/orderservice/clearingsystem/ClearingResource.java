package org.mockwizard.examples.orderservice.clearingsystem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/clearings")
@Produces(MediaType.APPLICATION_JSON)
public class ClearingResource {
    private MockClearingSystem clearingService;

    public ClearingResource(ClearingService clearingService) {
        this.clearingService = (MockClearingSystem) clearingService;
    }

    @GET
    @Path("{symbol}")
    public Clearing getClearing(@PathParam("symbol") String symbol) {
        return clearingService.get(symbol);
    }
}
