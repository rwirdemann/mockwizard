package org.orderservice.quoteservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

public class QuoteServiceFactory {

    @JsonProperty
    @NotEmpty
    private Type type;

    public QuoteService quoteService(Environment environment) {
        switch (type) {
            case mock:
                QuoteService quoteService = new MockQuoteService();
                environment.jersey().register(new QuoteServiceProvisionResource(quoteService));
                return quoteService;
            case real: return new RealQuoteService();
        }
        throw new RuntimeException("Unknown service type: " + type);
    }

    enum Type {
        mock, real;
    }
}
