package com.zuehlke.pizza.pizzaorderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zuehlke.pizza.pizzaorderservice.domain.Order;
import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;
import com.zuehlke.pizza.pizzaorderservice.service.PizzaOrderService;

@RestController
@RequestMapping("pizza-orders")
public class PizzaOrderRestController {

   private final PizzaOrderService pizzaOrderService;

   @Autowired
   public PizzaOrderRestController(PizzaOrderService pizzaOrderService) {
      this.pizzaOrderService = pizzaOrderService;
   }

   @GetMapping
   public List<Order> getOrders() {
      return pizzaOrderService.searchOrders();
   }

   @GetMapping("/{id}")
   public Order getOrder(@PathVariable int id) {
      return pizzaOrderService.getOrder(id);
   }

   @GetMapping("/type/{pizzaType}")
   public List<Order> getOrdersByType(@PathVariable PizzaType pizzaType) {
      return pizzaOrderService.getOrdersByType(pizzaType);
   }

   @PostMapping("generate")
   @ResponseStatus(value = HttpStatus.NO_CONTENT)
   public void generatePizzaOrder() {
      pizzaOrderService.generateOrder();
   }

}
