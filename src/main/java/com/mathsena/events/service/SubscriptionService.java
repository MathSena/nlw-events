package com.mathsena.events.service;

import com.mathsena.events.model.Event;
import com.mathsena.events.model.Subscription;
import com.mathsena.events.model.User;
import com.mathsena.events.repository.EventRepository;
import com.mathsena.events.repository.SubscriptionRepository;
import com.mathsena.events.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubscriptionService {

  @Autowired private EventRepository eventRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private SubscriptionRepository subscriptionRepository;

  public Subscription createNewSubscription(String eventName, User user) {
    log.info("Recuperando o evento...");

    Event event = eventRepository.findByPrettyName(eventName);

    log.info("Criando usuário..");
    user = userRepository.save(user);

    Subscription subs = new Subscription();
    subs.setEventId(event);
    subs.setSubscriber(user);

    log.info("Usuário inscrito");
    Subscription res = subscriptionRepository.save(subs);

    return res;
  }
}
