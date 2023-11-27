package com.zuehlke.pizza.pizzaorderservice.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<ProblemDto> handleIllegalArgumentException(IllegalArgumentException exception) {
      return ResponseEntity.status(NOT_FOUND).body(new ProblemDto(exception.getMessage()));
   }

   @ExceptionHandler(IllegalStateException.class)
   public ResponseEntity<ProblemDto> handleIllegalStateException(IllegalStateException exception) {
      return ResponseEntity.status(BAD_REQUEST).body(new ProblemDto(exception.getMessage()));
   }

}
