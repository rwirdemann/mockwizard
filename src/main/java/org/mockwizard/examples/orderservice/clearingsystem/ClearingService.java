package org.mockwizard.examples.orderservice.clearingsystem;

import org.mockwizard.examples.orderservice.Order;

public class ClearingService {

    public boolean clear(Order o) {
        System.out.println("clear");
        return true;
    }
}
