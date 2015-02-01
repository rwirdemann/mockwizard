package org.orderservice.quoteservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stubbing {

    @JsonProperty
    private String servicename;

    @JsonProperty
    private String methodname;

    @JsonProperty
    private String symbol;

    @JsonProperty
    private double price;

    public Stubbing() {
    }

    public Stubbing(String servicename, String methodname) {
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
}
