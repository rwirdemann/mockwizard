package org.mockwizard;

import org.mockito.Mockito;
import org.orderservice.quoteservice.QuoteService;
import org.orderservice.quoteservice.QuoteServiceProvision;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/stubiings")
public class StubbingResource {

    private Map<String, QuoteService> services = new HashMap<String, QuoteService>();

    public void addMock(String name, QuoteService quoteService) {
        services.put(name, quoteService);
    }

    @POST
    public void create(QuoteServiceProvision provision) {
        QuoteService quoteservice = services.get("quoteservice");
        Mockito.when(quoteservice.getPrice(provision.getSymbol())).thenReturn(provision.getPrice());
    }
}
