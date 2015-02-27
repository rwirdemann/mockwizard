package org.orderservice.quoteservice;

import org.mockwizard.MockableService;

public interface QuoteService extends MockableService {
    double getPrice(String symbol);
}
