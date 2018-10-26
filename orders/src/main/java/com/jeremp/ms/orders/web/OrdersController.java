package com.jeremp.ms.orders.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

  @Value("${my.firstname}")
  private String firstname;

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    String message = "Testing my healh check function";
    return new ResponseEntity<>(message, HttpStatus.OK);
  }

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    String message = "Hello I'm "+ firstname;
    return new ResponseEntity<>(message, HttpStatus.OK);
  }

}
