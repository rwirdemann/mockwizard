package org.mockwizard.examples.simpleservice;

import com.google.common.io.Resources;
import com.sun.jersey.api.client.Client;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockwizard.example.simpleservice.application.SimpleServiceApplication;
import org.mockwizard.example.simpleservice.application.SimpleServiceConfiguration;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class SimpleServiceTest {
    public static final String HOST = "http://localhost:9061";

    @ClassRule
    public static final DropwizardAppRule<SimpleServiceConfiguration> RULE =
            new DropwizardAppRule<SimpleServiceConfiguration>(SimpleServiceApplication.class, resourceFilePath("simple-service-config.yml"));

    @Test
    public void simpleMockTest() throws Exception {
        assertTrue(true);
    }

    private static String resourceFilePath(String s) {
        try {
            return new File(Resources.getResource(s).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
