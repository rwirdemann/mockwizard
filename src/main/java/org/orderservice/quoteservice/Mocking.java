package org.orderservice.quoteservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mockwizard.Param;

import java.util.ArrayList;
import java.util.List;

public class Mocking {

    @JsonProperty
    private String servicename;

    @JsonProperty
    private String methodname;

    @JsonProperty
    private String symbol;

    @JsonProperty
    private double price;

    @JsonProperty
    private List<Param> params = new ArrayList<Param>();

    public Mocking() {
    }

    public Mocking(String servicename, String methodname) {
        this.servicename = servicename;
        this.methodname = methodname;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getServicename() {
        return servicename;
    }

    public String getMethodname() {
        return methodname;
    }

    public void addParam(String s) {
        params.add(new Param(String.class, s));
    }
}
