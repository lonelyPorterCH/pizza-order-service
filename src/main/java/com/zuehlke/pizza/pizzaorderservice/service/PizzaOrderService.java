package com.zuehlke.pizza.pizzaorderservice.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zuehlke.pizza.pizzaorderservice.dataaccess.OrderRepository;
import com.zuehlke.pizza.pizzaorderservice.domain.Order;
import com.zuehlke.pizza.pizzaorderservice.domain.OrderItem;
import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;

@Service
public class PizzaOrderService {

   private final OrderRepository database;

   private final Random random = new Random();

   @Autowired
   public PizzaOrderService(OrderRepository database) {
      this.database = database;
   }

   public List<Order> searchOrders() {
      return database.getAllOrders();
   }

   public Order getOrder(int id) {
      return database.getOrderById(id);
   }

   public List<Order> getOrdersByType(PizzaType pizzaType) {
      return database.getOrdersByType(pizzaType);
   }

   public void generateOrder() {
      var id = database.nextAvailableId();
      var orderItem = generateOrderItem();

      database.addOrder(new Order(id, List.of(orderItem)));

   }

   private OrderItem generateOrderItem() {
      var amount = random.nextInt(3) + 1;

      var pizzaTypes = PizzaType.values();
      var randomIndex = random.nextInt(pizzaTypes.length);
      var randomPizzaType = pizzaTypes[randomIndex];

      return new OrderItem(amount, randomPizzaType);
   }

}
