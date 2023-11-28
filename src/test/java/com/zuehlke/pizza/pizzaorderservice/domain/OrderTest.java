package com.zuehlke.pizza.pizzaorderservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class OrderTest {

   @Test
   void contains() {
      var order = new Order(1, List.of(new OrderItem(1, PizzaType.MARGHERITA)), Channel.INTERNAL);

      assertThat(order.contains(PizzaType.MARGHERITA)).isTrue();
      assertThat(order.contains(PizzaType.TONNO)).isFalse();
   }

   @Test
   void getTotalQuantityOfOrderedPizzas() {
      var order = new Order(1,
         List.of(
            new OrderItem(3, PizzaType.MARGHERITA),
            new OrderItem(2, PizzaType.TONNO)
         ),
         Channel.INTERNAL);

      assertThat(order.getTotalQuantityOfOrderedPizzas()).isEqualTo(5);
   }

}