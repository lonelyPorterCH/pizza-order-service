package com.zuehlke.pizza.pizzaorderservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;

@Component
@Profile("prod")
public class RemotePizzaInventoryClient implements PizzaInventoryClient {

   //private final Random random = new Random();

   @Override
   public boolean isAvailable(PizzaType pizzaType) {
      //return random.nextBoolean();
      return true;
   }

}
