package org.orderservice.quoteservice;

import java.util.HashMap;
import java.util.Map;

public class MockQuoteService implements QuoteService {
    private Map<String, Stubbing> provisions = new HashMap<String, Stubbing>();

    @Override
    public double getPrice(String symbol) {
        if (provisions.containsKey(symbol)) {
            return provisions.get(symbol).getPrice();
        } else {
            return 200.;
        }
    }

    public void addQuoteProvision(Stubbing provision) {
        provisions.put(provision.getSymbol(), provision);
    }

    public void clear() {
        provisions.clear();
    }
}
