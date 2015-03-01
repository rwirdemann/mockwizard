package org.mockwizard.examples.sampleservice;

import com.google.common.io.Resources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockwizard.examples.orderservice.Order;

import javax.ws.rs.core.MediaType;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SampleServiceTest {
    public static final String HOST = "http://localhost:9060";
    private Client client = new Client();

    @ClassRule
    public static final DropwizardAppRule<SampleServiceConfiguration> RULE =
            new DropwizardAppRule<SampleServiceConfiguration>(SampleServiceApplication.class, resourceFilePath("sample-service-config.yml"));

    @Test
    public void simpleMockTest() throws Exception {
        WebResource resource = client.resource(HOST).path("samples/simple");
        ClientResponse clientResponse = resource.get(ClientResponse.class);
        assertEquals(200, clientResponse.getStatus());
    }

    private static String resourceFilePath(String s) {
        try {
            return new File(Resources.getResource(s).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
