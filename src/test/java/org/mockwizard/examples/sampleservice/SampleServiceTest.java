package org.mockwizard.examples.sampleservice;

import com.google.common.io.Resources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockwizard.Mockwizard;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SampleServiceTest {

    @ClassRule
    public static final DropwizardAppRule<SampleServiceConfiguration> RULE =
            new DropwizardAppRule<SampleServiceConfiguration>(SampleServiceApplication.class, resourceFilePath("sample-service-config.yml"));

    private SampleClient sampleClient = new SampleClient(RULE.getLocalPort());

    @BeforeClass
    public static void setUpClass() throws Exception {
        Mockwizard.setup(RULE.getLocalPort());
    }

    @Test
    public void mockNoParameter() throws Exception {
        Mockwizard.when("partnerservice.foo").thenReturn(1);
        assertEquals(1, sampleClient.foo());
    }

    @Test
    public void mockWithParameter() throws Exception {
        Mockwizard.when("partnerservice.foo").with("hello").thenReturn(2);
        Mockwizard.when("partnerservice.foo").with("hallo").thenReturn(3);
        
        assertEquals(2, sampleClient.foo("hello"));
        assertEquals(3, sampleClient.foo("hallo"));
    }

    private static String resourceFilePath(String s) {
        try {
            return new File(Resources.getResource(s).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class SampleClient {
        private final String baseUri;
        private Client client = new Client();

        public SampleClient(int localPort) {
            baseUri = "http://localhost:" + localPort;
        }

        int foo() {
            WebResource resource = client.resource(baseUri).path("samples/foo");
            ClientResponse clientResponse = resource.get(ClientResponse.class);
            return clientResponse.getEntity(Integer.class);
        }

        public int foo(String s) {
            WebResource resource = client.resource(baseUri).path("samples/foo").path(s);
            ClientResponse clientResponse = resource.get(ClientResponse.class);
            return clientResponse.getEntity(Integer.class);
        }
    }
}
