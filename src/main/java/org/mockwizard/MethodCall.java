package org.mockwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class MethodCall<T> {

    @JsonProperty
    private String servicename;

    @JsonProperty
    private String methodname;

    @JsonProperty
    private List<Param> params = new ArrayList<Param>();

    private T returnValue;

    public MethodCall() {
    }

    public MethodCall(String servicename, String methodname) {
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

    public void addParam(Boolean i) {
        params.add(new Param(Boolean.class, i));
    }

    public void addParam(Double d) {
        params.add(new Param(Double.class, d));
    }

    public void addParam(Class c) {
        params.add(new Param(c, null));
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public T getReturnValue() {
        return returnValue;
    }
}