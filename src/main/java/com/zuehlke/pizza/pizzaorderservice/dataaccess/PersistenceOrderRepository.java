package com.zuehlke.pizza.pizzaorderservice.dataaccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.zuehlke.pizza.pizzaorderservice.dataaccess.mapper.OrderMapper;
import com.zuehlke.pizza.pizzaorderservice.domain.Order;
import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;

@Repository
@Profile("prod")
public class PersistenceOrderRepository implements OrderRepository {

   private final MongoOrderRepository repository;

   @Autowired
   public PersistenceOrderRepository(MongoOrderRepository repository) {
      this.repository = repository;
   }

   @Override
   public List<Order> getAllOrders() {
      return repository.findAll().stream().map(OrderMapper::map).toList();
   }

   @Override
   public void addOrder(Order order) {
      System.out.println("Saving order to MongoDB: " + order.id());
      repository.save(OrderMapper.map(order));
   }

   @Override
   public long nextAvailableId() {
      return repository.count() + 1;
   }

   @Override
   public Order getOrderById(int id) {
      var orderDto = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order " + id + " does not exist"));

      return OrderMapper.map(orderDto);
   }

   @Override
   public List<Order> getOrdersByType(PizzaType pizzaType) {
      throw new UnsupportedOperationException();
   }

}
