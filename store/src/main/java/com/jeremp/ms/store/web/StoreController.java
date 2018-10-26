package com.jeremp.ms.store.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@RestController
public class StoreController {

  Logger logger = LoggerFactory.getLogger(StoreController.class);

  @Autowired
  private DiscoveryClient discoveryClient;

  private RestTemplate restTemplate = new RestTemplate();

  @GetMapping("/health")
  public ResponseEntity<String> health() {
    String message = "Testing my healh check function";
    return new ResponseEntity<>(message, HttpStatus.OK);
  }

  @GetMapping("/getorder")
  public ResponseEntity<String> getOrder() {
    Optional<URI> optURI = serviceUrl();
    if(optURI.isPresent()){
      String fullURL = optURI.get().toString() + "/hello";
      logger.info("Let's call {}", fullURL);
      ResponseEntity<String> remoteResponse = restTemplate.getForEntity(fullURL, String.class);
      return new ResponseEntity<>("you hit it : "+remoteResponse.getBody(), HttpStatus.OK);
    }else{
      logger.warn("No service available !");
      return new ResponseEntity<>("no order service up... sorry", HttpStatus.OK);
    }

  }


  public Optional<URI> serviceUrl() {
    return discoveryClient.getInstances("orders")
            .stream()
            .map(si -> si.getUri())
          .findFirst();
  }

}
