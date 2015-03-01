package org.mockwizard.examples.orderservice.application;

import com.mongodb.Mongo;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mockwizard.Mockwizard;
import org.mockwizard.examples.DummyHealthCheck;
import org.mockwizard.examples.orderservice.OrderRepository;
import org.mockwizard.examples.orderservice.OrderResource;
import org.mockwizard.examples.orderservice.quoteservice.QuoteService;

public class OrderServiceApplication extends Application<OrderServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<OrderServiceConfiguration> quoteServiceConfigurationBootstrap) {
    }

    @Override
    public void run(OrderServiceConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);

        Mockwizard.init(environment);

        environment.jersey().register(new OrderResource(new OrderRepository(mongo.getDB("orderservice")),
                configuration.quoteServiceFactory.quoteService(QuoteService.class),
                configuration.clearingServiceFactory.clearingService(environment)));

        environment.healthChecks().register("dummy_health_check", new DummyHealthCheck());
    }

    public static void main(String[] args) throws Exception {
        new OrderServiceApplication().run(args);
    }
}
