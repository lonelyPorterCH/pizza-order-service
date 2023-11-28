package com.zuehlke.pizza.pizzaorderservice.service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zuehlke.pizza.pizzaorderservice.dataaccess.OrderRepository;
import com.zuehlke.pizza.pizzaorderservice.domain.Channel;
import com.zuehlke.pizza.pizzaorderservice.domain.Order;
import com.zuehlke.pizza.pizzaorderservice.domain.OrderItem;
import com.zuehlke.pizza.pizzaorderservice.domain.PizzaType;
import com.zuehlke.pizza.pizzaorderservice.service.processor.OrderProcessor;

@Service
public class PizzaOrderService {

   private final OrderRepository database;

   private final PizzaInventoryClient pizzaInventoryClient;

   private final Map<Channel, OrderProcessor> orderProcessors;

   private final Random random = new Random();

   @Autowired
   public PizzaOrderService(OrderRepository database, PizzaInventoryClient pizzaInventoryClient, List<OrderProcessor> orderProcessors) {
      this.database = database;
      this.pizzaInventoryClient = pizzaInventoryClient;
      this.orderProcessors = orderProcessors
         .stream()
         .collect(Collectors.toMap(
            OrderProcessor::canHandle,
            Function.identity()
         ));
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
      var channel = generateOrderChannel();

      addOrder(new Order(id, List.of(orderItem), channel));
   }

   private Channel generateOrderChannel() {
      var channels = Channel.values();
      int index = random.nextInt(channels.length);

      return channels[index];
   }

   public void addOrder(Order order) {
      boolean allAvailable = order.orderItems().stream().map(OrderItem::pizzaType).allMatch(pizzaInventoryClient::isAvailable);
      if (!allAvailable) {
         throw new IllegalStateException("Not all Pizzas are available");
      }

      OrderProcessor processor = orderProcessors.get(order.channel());
      if (processor != null) {
         processor.processOrder(order);
         database.addOrder(order);
      } else {
         throw new IllegalArgumentException("Unsupported order channel: " + order.channel());
      }
   }

   private OrderItem generateOrderItem() {
      var amount = random.nextInt(3) + 1;

      var pizzaTypes = PizzaType.values();
      var randomIndex = random.nextInt(pizzaTypes.length);
      var randomPizzaType = pizzaTypes[randomIndex];

      return new OrderItem(amount, randomPizzaType);
   }

}
