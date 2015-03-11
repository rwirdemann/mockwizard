package org.mockwizard;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/mockings")
public class MockingResource {

    @POST
    public void create(MethodCall methodCall) throws Exception {
        Mockwizard.stub(methodCall);
    }
}
