package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class CollaboratorFactory<T> {

    @JsonProperty
    @NotEmpty
    private ServiceType type;

    public <T> T service(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        switch (type) {
            case MOCK:
                return Mockwizard.stub(tClass);
            case REAL:
                return tClass.newInstance();
        }
        throw new RuntimeException("Unknown service type: " + type);
    }
}
