package com.zuehlke.pizza.pizzaorderservice.domain;

import java.util.List;

public record Order(long id, List<OrderItem> orderItems, Channel channel) {

   public boolean contains(PizzaType pizzaType) {
      return orderItems.stream().anyMatch(orderItem -> orderItem.pizzaType().equals(pizzaType));
   }

   public int getTotalQuantityOfOrderedPizzas() {
      return orderItems.stream()
         .mapToInt(OrderItem::amount)
         .sum();
   }

}
