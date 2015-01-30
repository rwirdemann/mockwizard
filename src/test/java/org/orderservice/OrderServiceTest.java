package org.orderservice;

import com.google.common.io.Resources;
import com.mongodb.Mongo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.orderservice.application.OrderServiceApplication;
import org.orderservice.application.OrderServiceConfiguration;
import org.orderservice.clearingsystem.Clearing;
import org.orderservice.quoteservice.QuoteService;
import org.orderservice.quoteservice.QuoteServiceProvision;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OrderServiceTest {
    public static final String HOST = "http://localhost:9050";
    private Client client;

    @ClassRule
    public static final DropwizardAppRule<OrderServiceConfiguration> RULE =
            new DropwizardAppRule<OrderServiceConfiguration>(OrderServiceApplication.class, resourceFilePath("configuration.yml"));


    @Before
    public void setUp() throws Exception {
        client = new Client();
        Mongo mongo = new Mongo(RULE.getConfiguration().mongohost, RULE.getConfiguration().mongoport);
        OrderRepository orderRepository = new OrderRepository(mongo.getDB(RULE.getConfiguration().mongodb));
        orderRepository.delete();

        WebResource provisionResource = client.resource(HOST).path("quoteserviceprovisions");
        provisionResource.delete();
    }

    @Test
    public void shouldGetOrders() throws Exception {
        // GIVEN
        WebResource resource = client.resource(HOST).path("orders");
        Order o = new Order("TSLA", 5, 200.0);
        resource.type(MediaType.APPLICATION_JSON_TYPE).entity(o).post();

        // WHEN
        List<Order> orders = resource.get(List.class);

        // THEN
        assertThat(orders.size(), is(1));
    }

    @Test
    public void shouldDenyOrder() throws Exception {
        QuoteService quoteService = Mockito.mock(QuoteService.class);

        // GIVEN: Limit exceeding order price
        Mockwizard.when(quoteService.getPrice("TSLA")).thenReturn(210.0);

        // WHEN: Order requested
        WebResource resource = client.resource(HOST).path("orders");
        Order o = new Order("TSLA", 5, 200.0);
        resource.type(MediaType.APPLICATION_JSON_TYPE).entity(o).post();

        // THEN: Order was denied
        List<Order> orders = resource.get(List.class);
        assertThat(orders.isEmpty(), is(true));
    }

    @Test
    public void shouldClearOrder() throws Exception {
        // WHEN: Order requested
        WebResource resource = client.resource(HOST).path("orders");
        Order o = new Order("TSLA", 5, 200.0);
        resource.type(MediaType.APPLICATION_JSON_TYPE).entity(o).post();

        // THEN: Order was cleared
        WebResource clearingResource = client.resource(HOST).path("clearings");
        Clearing clearings = clearingResource.path("TSLA").get(Clearing.class);
        assertThat(clearings.getCount(), is(1));
    }

    private static String resourceFilePath(String s) {
        try {
            return new File(Resources.getResource(s).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
