package org.orderservice.quoteservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;
import org.mockito.Mockito;
import org.mockwizard.MockingResource;
import org.mockwizard.ServiceType;

public class QuoteServiceFactory {

    @JsonProperty
    @NotEmpty
    private ServiceType type;

    public QuoteService quoteService(Environment environment, MockingResource mockValueResource) {
        switch (type) {
            case MOCK:
                QuoteService quoteService = Mockito.mock(QuoteService.class);
                mockValueResource.addMock("quoteservice", quoteService);
                return quoteService;
            case REAL:
                return new RealQuoteService();
        }
        throw new RuntimeException("Unknown service type: " + type);
    }
}
