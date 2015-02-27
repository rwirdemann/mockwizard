package org.orderservice.quoteservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.mockito.Mockito;
import org.mockwizard.Mockwizard;
import org.mockwizard.ServiceType;

public class QuoteServiceFactory {

    @JsonProperty
    @NotEmpty
    private ServiceType type;

    public QuoteService quoteService() {
        switch (type) {
            case MOCK:
                return Mockwizard.mock(QuoteService.class);
            case REAL:
                return new QuoteService();
        }
        throw new RuntimeException("Unknown service type: " + type);
    }
}
