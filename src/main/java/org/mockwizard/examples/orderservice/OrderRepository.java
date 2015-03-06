package org.mockwizard.examples.orderservice;

import java.util.*;

public class OrderRepository {
    private Map<String, Order> orders = new HashMap<String, Order>();

    public List<Order> find() {
        return new ArrayList<Order>(orders.values());
    }

    public void delete() {
        orders.clear();
    }

    public void create(Order order) {
        order.set_id(UUID.randomUUID().toString());
        orders.put(order.get_id(), order);
    }
}
