package org.orderservice.quoteservice;

import org.mockwizard.MockableService;

public class RealQuoteService implements QuoteService, MockableService {

    @Override
    public double getPrice(String symbol) {
        return 0;
    }
}
