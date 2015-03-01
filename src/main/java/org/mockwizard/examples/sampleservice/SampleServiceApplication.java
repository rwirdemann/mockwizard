package org.mockwizard.examples.sampleservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mockwizard.Mockwizard;
import org.mockwizard.examples.DummyHealthCheck;

public class SampleServiceApplication extends Application<SampleServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<SampleServiceConfiguration> quoteServiceConfigurationBootstrap) {
    }

    @Override
    public void run(SampleServiceConfiguration configuration, Environment environment) throws Exception {
        Mockwizard.init(environment);
        environment.jersey().register(new SampleResource(configuration.partnerServiceFactory.quoteService(PartnerService.class)));
        environment.healthChecks().register("dummy_health_check", new DummyHealthCheck());
    }

    public static void main(String[] args) throws Exception {
        new SampleServiceApplication().run(args);
    }
}
