package org.orderservice.application;

import com.mongodb.Mongo;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mockwizard.MockingResource;
import org.mockwizard.Mockwizard;
import org.orderservice.OrderRepository;
import org.orderservice.OrderResource;
import org.orderservice.quoteservice.QuoteService;

public class OrderServiceApplication extends Application<OrderServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<OrderServiceConfiguration> quoteServiceConfigurationBootstrap) {
    }

    @Override
    public void run(OrderServiceConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);

        Mockwizard.init(environment);

        QuoteService o = (QuoteService) configuration.quoteServiceFactory.quoteService(QuoteService.class);

        environment.jersey().register(new OrderResource(new OrderRepository(mongo.getDB("orderservice")),
                o,
                configuration.clearingServiceFactory.clearingService(environment)));
    }

    public static void main(String[] args) throws Exception {
        new OrderServiceApplication().run(args);
    }
}
