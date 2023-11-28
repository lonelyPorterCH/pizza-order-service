package com.zuehlke.pizza.pizzaorderservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PizzaOrderRestControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   void getOrder() throws Exception {
      String expected = """
         {
           "id": 1,
           "orderItems": [
             {
               "amount": 1,
               "pizzaType": "MARGHERITA"
             }
           ],
           "channel": "INTERNAL"
         }
         """;
      mockMvc.perform(get("/pizza-orders/1")).andExpect(status().isOk()).andExpect(content().json(expected));
   }

   @Test
   void generatePizzaOrder() throws Exception {
      mockMvc.perform(post("/pizza-orders/generate")).andExpect(status().isCreated());
   }

}