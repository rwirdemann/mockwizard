package org.mockwizard.examples.orderservice;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.MediaType;
import java.net.URI;
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

    public String create(Order o) {
        WebResource resource = client.resource(baseUri).path("orders");
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON_TYPE).entity(o).post(ClientResponse.class);
        String uri = response.getLocation().toString();
        return StringUtils.substringAfterLast(uri, "/");
    }

    public Order get(String orderId) {
        WebResource resource = client.resource(baseUri).path("orders").path(orderId);
        return resource.get(Order.class);
    }

    public List<Order> all() {
        return client.resource(baseUri).path("orders").get(List.class);
    }

}
