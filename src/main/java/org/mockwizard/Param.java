package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Param<T> {

    @JsonProperty
    private Class<T> aClass;

    @JsonProperty
    private T value;

    public Param() {
    }

    public Param(Class<T> aClass, T value) {
        this.aClass = aClass;
        this.value = value;
    }

    public Class<T> getaClass() {
        return aClass;
    }

    public T getValue() {
        return value;
    }
}
