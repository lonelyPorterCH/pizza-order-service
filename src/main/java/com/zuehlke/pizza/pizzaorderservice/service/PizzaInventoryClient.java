package com.zuehlke.pizza.pizzaorderservice.service;

import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;

public interface PizzaInventoryClient {

   boolean isAvailable(PizzaType pizzaType);

}
