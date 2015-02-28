package org.mockwizard.example.simpleservice.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mockwizard.Mockwizard;

public class SimpleServiceApplication extends Application<SimpleServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<SimpleServiceConfiguration> quoteServiceConfigurationBootstrap) {
    }

    @Override
    public void run(SimpleServiceConfiguration configuration, Environment environment) throws Exception {
        Mockwizard.init(environment);
    }

    public static void main(String[] args) throws Exception {
        new SimpleServiceApplication().run(args);
    }
}
