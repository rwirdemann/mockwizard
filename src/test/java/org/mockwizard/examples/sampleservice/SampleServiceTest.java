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
import org.mockwizard.Mockwizard;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SampleServiceTest {
    public static final String HOST = "http://localhost:9060";

    @ClassRule
    public static final DropwizardAppRule<SampleServiceConfiguration> RULE =
            new DropwizardAppRule<SampleServiceConfiguration>(SampleServiceApplication.class, resourceFilePath("sample-service-config.yml"));
    
    private SampleClient sampleClient;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Mockwizard.setup(RULE.getLocalPort());
    }

    @Before
    public void setUp() throws Exception {
        sampleClient = new SampleClient();
    }

    @Test
    public void mockNoParameter() throws Exception {
        Mockwizard.when("partnerservice.foo").thenReturn(1);
        assertEquals(1, sampleClient.foo());
    }

    @Test
    public void mockWithParameter() throws Exception {
        Mockwizard.when("partnerservice.foo").with("hello").thenReturn(2);
        assertEquals(2, sampleClient.foo("hello"));
    }

    private static String resourceFilePath(String s) {
        try {
            return new File(Resources.getResource(s).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class SampleClient {
        private Client client = new Client();

        int foo() {
            WebResource resource = client.resource(HOST).path("samples/foo");
            ClientResponse clientResponse = resource.get(ClientResponse.class);
            return clientResponse.getEntity(Integer.class);
        }

        public int foo(String s) {
            WebResource resource = client.resource(HOST).path("samples/foo").path(s);
            ClientResponse clientResponse = resource.get(ClientResponse.class);
            return clientResponse.getEntity(Integer.class);
        }
    }
}
