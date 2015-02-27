package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.mockito.Mockito;
import org.mockwizard.Mockwizard;
import org.mockwizard.ServiceType;

public class ServiceFactory<T> {

    @JsonProperty
    @NotEmpty
    private ServiceType type;

    public <T> T quoteService(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        switch (type) {
            case MOCK:
                return Mockwizard.mock(tClass);
            case REAL:
                return tClass.newInstance();
        }
        throw new RuntimeException("Unknown service type: " + type);
    }
}
