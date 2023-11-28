package com.zuehlke.pizza.pizzaorderservice.dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.zuehlke.pizza.pizzaorderservice.domain.Channel;
import com.zuehlke.pizza.pizzaorderservice.domain.Order;
import com.zuehlke.pizza.pizzaorderservice.domain.OrderItem;
import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;

@Repository
@Profile("default")
public class InMemoryOrderRepository implements OrderRepository {

   private final List<Order> DATABASE = new ArrayList<>(List.of(new Order(1, List.of(new OrderItem(1, PizzaType.MARGHERITA)), Channel.INTERNAL)));

   private int count = 1;

   @Override
   public List<Order> getAllOrders() {
      return List.copyOf(DATABASE);
   }

   @Override
   public Order getOrderById(int id) {
      return searchOrder(id).orElseThrow(() -> new IllegalArgumentException("Order " + id + " does not exist"));
   }

   @Override
   public List<Order> getOrdersByType(PizzaType pizzaType) {
      return DATABASE.stream().filter(order -> order.contains(pizzaType)).toList();
   }

   @Override
   public void addOrder(Order order) {
      if (searchOrder(order.id()).isPresent()) {
         throw new IllegalArgumentException("Order " + order.id() + " already exists");
      }
      DATABASE.add(order);
      count++;
   }

   @Override
   public long nextAvailableId() {
      return count + 1;
   }

   private Optional<Order> searchOrder(long id) {
      return DATABASE.stream()
         .filter(order -> id == order.id())
         .findFirst();
   }

}
