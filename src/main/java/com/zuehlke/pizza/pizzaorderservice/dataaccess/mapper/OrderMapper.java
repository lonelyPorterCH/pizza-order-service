package com.zuehlke.pizza.pizzaorderservice.dataaccess.mapper;

import com.zuehlke.pizza.pizzaorderservice.dataaccess.dto.OrderDto;
import com.zuehlke.pizza.pizzaorderservice.domain.Order;

public class OrderMapper {

   public static OrderDto map(Order order) {
      return new OrderDto(order.id(), order.orderItems(), order.channel());
   }

   public static Order map(OrderDto orderDto) {
      return new Order(orderDto.getId(), orderDto.getOrderItems(), orderDto.getChannel());
   }

}
