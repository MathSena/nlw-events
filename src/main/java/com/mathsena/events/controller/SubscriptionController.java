package com.mathsena.events.controller;

import com.mathsena.events.model.Subscription;
import com.mathsena.events.model.User;
import com.mathsena.events.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

  @Autowired private SubscriptionService service;

  @PostMapping("/subs/{prettyName}")
  public ResponseEntity<Subscription> createNewSubscription(
      @PathVariable String prettyName, @RequestBody User subscriber) {
    Subscription res = service.createNewSubscription(prettyName, subscriber);
    if (res != null) {
      return ResponseEntity.ok(res);
    }
    return ResponseEntity.badRequest().build();
  }
}
