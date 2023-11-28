package com.zuehlke.pizza.pizzaorderservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class OrderTest {

   Order order = new Order(1, List.of(new OrderItem(1, PizzaType.MARGHERITA)), Channel.INTERNAL);

   @Test
   void contains() {
      boolean contains = order.contains(PizzaType.MARGHERITA);

      assertThat(contains).isTrue();
   }

}