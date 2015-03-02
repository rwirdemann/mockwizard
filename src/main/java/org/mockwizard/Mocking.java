package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class Mocking<T> {

    @JsonProperty
    private String servicename;

    @JsonProperty
    private String methodname;

    @JsonProperty
    private List<Param> params = new ArrayList<Param>();

    private T returnValue;

    public Mocking() {
    }

    public Mocking(String servicename, String methodname) {
        this.servicename = servicename;
        this.methodname = methodname;
    }

    public String getServicename() {
        return servicename;
    }

    public String getMethodname() {
        return methodname;
    }

    public List<Param> getParams() {
        return params;
    }

    public void addParam(String s) {
        params.add(new Param(String.class, s));
    }

    public void addParam(Integer i) {
        params.add(new Param(Integer.class, i));
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public T getReturnValue() {
        return returnValue;
    }
}
