package org.orderservice.application;

import com.mongodb.Mongo;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mockwizard.MockingResource;
import org.orderservice.OrderRepository;
import org.orderservice.OrderResource;

public class OrderServiceApplication extends Application<OrderServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<OrderServiceConfiguration> quoteServiceConfigurationBootstrap) {
    }

    @Override
    public void run(OrderServiceConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);

        MockingResource mockValueResource = new MockingResource();
        environment.jersey().register(mockValueResource);
        environment.jersey().register(new OrderResource(new OrderRepository(mongo.getDB("orderservice")),
                configuration.quoteServiceFactory.quoteService(environment, mockValueResource),
                configuration.clearingServiceFactory.clearingService(environment)));
    }

    public static void main(String[] args) throws Exception {
        new OrderServiceApplication().run(args);
    }
}
