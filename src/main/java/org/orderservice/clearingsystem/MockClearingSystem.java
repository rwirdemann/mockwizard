package org.orderservice.clearingsystem;

import org.orderservice.Order;

import java.util.HashMap;
import java.util.Map;

public class MockClearingSystem implements ClearingService {
    private Map<String, Clearing> clearings = new HashMap<String, Clearing>();

    @Override
    public void clear(Order order) {
        if (clearings.containsKey(order.getSymbol())) {
            clearings.get(order.getSymbol()).incCount();
        } else {
            clearings.put(order.getSymbol(), new Clearing());
        }
    }

    public Clearing get(String symbol) {
        return clearings.get(symbol);
    }
}
