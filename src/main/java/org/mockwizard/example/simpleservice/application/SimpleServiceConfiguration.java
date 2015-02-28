package org.mockwizard.example.simpleservice.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.mockwizard.ServiceFactory;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingServiceFactory;
import org.mockwizard.examples.orderservice.quoteservice.QuoteService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SimpleServiceConfiguration extends Configuration {
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
    public ServiceFactory<QuoteService> quoteServiceFactory;

    @JsonProperty("clearingService")
    public ClearingServiceFactory clearingServiceFactory;
}
