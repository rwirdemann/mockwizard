package org.orderservice.quoteservice;

import java.util.HashMap;
import java.util.Map;

public class MockQuoteService implements QuoteService {
    private Map<String, Mocking> provisions = new HashMap<String, Mocking>();

    @Override
    public double getPrice(String symbol) {
        if (provisions.containsKey(symbol)) {
            return provisions.get(symbol).getPrice();
        } else {
            return 200.;
        }
    }

    public void addQuoteProvision(Mocking provision) {
        provisions.put(provision.getSymbol(), provision);
    }

    public void clear() {
        provisions.clear();
    }
}
