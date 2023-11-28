package com.zuehlke.pizza.pizzaorderservice.service.processor;

import com.zuehlke.pizza.pizzaorderservice.domain.Channel;
import com.zuehlke.pizza.pizzaorderservice.domain.Order;

public interface OrderProcessor {

   default void processOrder(Order order) {
      System.out.println("Handle Order " + order.id() + " with channel: " + canHandle());
   }

   Channel canHandle();

}
