package com.mathsena.events.controller;

import com.mathsena.events.dto.ErrorMessage;
import com.mathsena.events.dto.SubscriptionResponse;
import com.mathsena.events.exception.EventNotFoundException;
import com.mathsena.events.exception.SubscriptionConflictException;
import com.mathsena.events.exception.UserNotFoundException;
import com.mathsena.events.model.User;
import com.mathsena.events.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

  @Autowired private SubscriptionService service;

  @PostMapping({"/subs/{prettyName}", "/subs/{prettyName}/{userId}"})
  public ResponseEntity<?> createNewSubscription(
      @PathVariable String prettyName,
      @RequestBody User subscriber,
      @PathVariable(required = false) Integer userId) {
    try {
      SubscriptionResponse res = service.createNewSubscription(prettyName, subscriber, userId);
      if (res != null) {
        return ResponseEntity.ok(res);
      }
    } catch (EventNotFoundException | UserNotFoundException e) {
      return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
    } catch (SubscriptionConflictException e) {
      return ResponseEntity.status(409).body(new ErrorMessage(e.getMessage()));
    }

    return ResponseEntity.badRequest().build();
  }
}
