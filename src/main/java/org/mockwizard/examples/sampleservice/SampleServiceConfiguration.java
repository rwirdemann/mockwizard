package org.mockwizard.examples.sampleservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.mockwizard.ServiceFactory;
import org.mockwizard.examples.orderservice.clearingsystem.ClearingServiceFactory;
import org.mockwizard.examples.orderservice.quoteservice.QuoteService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SampleServiceConfiguration extends Configuration {

    @JsonProperty("partnerService")
    public ServiceFactory<PartnerService> partnerServiceFactory;
}
