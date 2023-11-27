package com.zuehlke.pizza.pizzaorderservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;

@Component
@Profile("default")
public class LocalPizzaInventoryClient implements PizzaInventoryClient {

   @Override
   public boolean isAvailable(PizzaType pizzaType) {
      return true;
   }

}
