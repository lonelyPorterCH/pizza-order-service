package com.zuehlke.pizza.pizzaorderservice.dataaccess.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zuehlke.pizza.pizzaorderservice.domain.Channel;
import com.zuehlke.pizza.pizzaorderservice.domain.OrderItem;

@Document
public class OrderDto {

   private final List<OrderItem> orderItems;

   private final Channel channel;

   @Id
   private int id;

   public OrderDto(int id, List<OrderItem> orderItems, Channel channel) {
      this.id = id;
      this.orderItems = orderItems;
      this.channel = channel;
   }

   public int getId() {
      return id;
   }

   public List<OrderItem> getOrderItems() {
      return orderItems;
   }

   public Channel getChannel() {
      return channel;
   }

}
