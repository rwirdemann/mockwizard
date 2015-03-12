package org.mockwizard.examples.orderservice;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class OrderServiceClient {

    private final String baseUri;
    private final Client client = new Client();

    public OrderServiceClient(int port) {
        this.baseUri = "http://localhost:" + port;
    }

    public void delete() {
        client.resource(baseUri).path("orders").delete();
    }

    public void create(Order o) {
        WebResource resource = client.resource(baseUri).path("orders");
        resource.type(MediaType.APPLICATION_JSON_TYPE).entity(o).post();
    }

    public List<Order> all() {
        return client.resource(baseUri).path("orders").get(List.class);
    }

}
