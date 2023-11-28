package com.zuehlke.pizza.pizzaorderservice.dataaccess;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zuehlke.pizza.pizzaorderservice.dataaccess.dto.OrderDto;

@Repository
public interface MongoOrderRepository extends MongoRepository<OrderDto, Integer> {

}
