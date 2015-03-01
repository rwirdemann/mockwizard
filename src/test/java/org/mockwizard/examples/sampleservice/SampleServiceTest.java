package org.mockwizard.examples.sampleservice;

import com.google.common.io.Resources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockwizard.Mockwizard;
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

    @BeforeClass
    public static void setUp() throws Exception {
        Mockwizard.setup(RULE.getLocalPort());
    }

    @Test
    public void simpleMockTest() throws Exception {
        Mockwizard.when("partnerservice.foo").thenReturn(0);
        
        WebResource resource = client.resource(HOST).path("samples/foo");
        ClientResponse clientResponse = resource.get(ClientResponse.class);
        assertEquals(200, clientResponse.getStatus());
        assertEquals(Integer.valueOf(0), clientResponse.getEntity(Integer.class));
    }

    private static String resourceFilePath(String s) {
        try {
            return new File(Resources.getResource(s).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
