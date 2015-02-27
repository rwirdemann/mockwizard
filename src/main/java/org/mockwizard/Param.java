package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Param<T> {

    @JsonProperty
    private Class<T> aClass;

    @JsonProperty
    private String value;

    public Param() {
    }

    public Param(Class<T> aClass, String value) {
        this.aClass = aClass;
        this.value = value;
    }

    public Class<T> getaClass() {
        return aClass;
    }

    public String getValue() {
        return value;
    }
}
