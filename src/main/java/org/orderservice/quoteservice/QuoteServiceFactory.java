package org.orderservice.quoteservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;
import org.mockito.Mockito;
import org.mockwizard.StubbingResource;

public class QuoteServiceFactory {

    @JsonProperty
    @NotEmpty
    private Type type;

    public QuoteService quoteService(Environment environment, StubbingResource mockValueResource) {
        switch (type) {
            case mock:
                QuoteService quoteService = Mockito.mock(QuoteService.class);
                mockValueResource.addMock("quoteservice", quoteService);
                return quoteService;
            case real: return new RealQuoteService();
        }
        throw new RuntimeException("Unknown service type: " + type);
    }

    enum Type {
        mock, real;
    }
}
