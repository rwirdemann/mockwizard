package org.orderservice.clearingsystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

public class ClearingServiceFactory {

    @JsonProperty
    @NotEmpty
    private Type type;

    public ClearingService clearingService(Environment environment) {
        switch (type) {
            case mock:
                ClearingService clearingService = new MockClearingSystem();
                environment.jersey().register(new ClearingResource(clearingService));
                return clearingService;
            case real: return new RealClearingService();
        }
        throw new RuntimeException("Unknown service type: " + type);
    }

    enum Type {
        mock, real;
    }
}
