package org.mockwizard.examples.orderservice.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingServiceFactory;
import org.mockwizard.examples.orderservice.quoteservice.QuoteService;
import org.mockwizard.CollaboratorFactory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OrderServiceConfiguration extends Configuration {
    @JsonProperty
    @NotEmpty
    public String mongohost = "127.0.0.1";

    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    public String mongodb = "orderservice";

    @JsonProperty("quoteService")
    public CollaboratorFactory<QuoteService> quoteServiceFactory;

    @JsonProperty("clearingService")
    public ClearingServiceFactory clearingServiceFactory;
}
