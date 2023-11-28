package com.zuehlke.pizza.pizzaorderservice.service.processor;

import org.springframework.stereotype.Service;

import com.zuehlke.pizza.pizzaorderservice.domain.Channel;

@Service
public class MobileOrderProcessor implements OrderProcessor {

   @Override
   public Channel canHandle() {
      return Channel.MOBILE;
   }

}
