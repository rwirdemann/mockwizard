package org.mockwizard;

import org.mockito.Mockito;
import org.orderservice.quoteservice.QuoteService;
import org.orderservice.quoteservice.Stubbing;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/stubbings")
public class StubbingResource {

    private Map<String, QuoteService> services = new HashMap<String, QuoteService>();

    public void addMock(String name, QuoteService quoteService) {
        services.put(name, quoteService);
    }

    @POST
    public void create(Stubbing stubbing) {
        QuoteService quoteservice = services.get(stubbing.getServicename());
        //quoteservice.getClass().getDeclaredMethod(stubbing.getMethodname());
        Mockito.when(quoteservice.getPrice(stubbing.getSymbol())).thenReturn(stubbing.getPrice());
    }
}
