package org.orderservice.quoteservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteServiceProvision {

    @JsonProperty
    private String symbol;

    @JsonProperty
    private double price;

    public QuoteServiceProvision() {
    }

    public QuoteServiceProvision(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}
