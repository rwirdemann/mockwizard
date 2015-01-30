package org.orderservice.clearingsystem;

import org.orderservice.Order;

public interface ClearingService {

    void clear(Order order);
}
