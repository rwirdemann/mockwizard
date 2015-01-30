package org.orderservice.quoteservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/quoteserviceprovisions")
public class QuoteServiceProvisionResource {
    private MockQuoteService quoteService;

    public QuoteServiceProvisionResource(QuoteService quoteService) {
        this.quoteService = (MockQuoteService) quoteService;
    }

    @POST
    public void create(QuoteServiceProvision provision) {
        quoteService.addQuoteProvision(provision);
    }

    @DELETE
    public void clear() {
        quoteService.clear();
    }
}
