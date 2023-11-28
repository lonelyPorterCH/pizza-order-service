package com.zuehlke.pizza.pizzaorderservice.dataaccess;

import java.util.List;

import com.zuehlke.pizza.pizzaorderservice.domain.Order;
import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;

public interface OrderRepository {

   List<Order> getAllOrders();

   Order getOrderById(int id);

   List<Order> getOrdersByType(PizzaType pizzaType);

   void addOrder(Order order);

   long nextAvailableId();

}
