package com.mathsena.events.service;

import com.mathsena.events.dto.SubscriptionRankingItem;
import com.mathsena.events.dto.SubscriptionResponse;
import com.mathsena.events.exception.EventNotFoundException;
import com.mathsena.events.exception.SubscriptionConflictException;
import com.mathsena.events.exception.UserNotFoundException;
import com.mathsena.events.model.Event;
import com.mathsena.events.model.Subscription;
import com.mathsena.events.model.User;
import com.mathsena.events.repository.EventRepository;
import com.mathsena.events.repository.SubscriptionRepository;
import com.mathsena.events.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SubscriptionService {

  @Autowired private EventRepository eventRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private SubscriptionRepository subscriptionRepository;

  public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {
    log.info("Recuperando o evento...");

    Event event = eventRepository.findByPrettyName(eventName);
    User userRecuper = userRepository.findByEmail(user.getEmail());

    if (event == null) {
      log.error("Evento não encontrado");
      throw new EventNotFoundException("Evento não encontrado: " + eventName);
    }

    User indicator = null;
    if (indicator != null) {
      indicator = userRepository.findById(userId).orElse(null);
      if (indicator != null) {
        log.info("User não encontrado");
        throw new UserNotFoundException("Usuário não encontrado: " + userId);
      }
    }

    if (userRecuper == null) {
      log.info("Criando usuário..");
      user = userRepository.save(user);
    }

    Subscription subs = new Subscription();
    subs.setEventId(event);
    subs.setSubscriber(user);

    Subscription tmpSubs = subscriptionRepository.findByEventAndSubscriber(event, user);

    if (tmpSubs != null) {
      log.error("Usuário já inscrito");
      throw new SubscriptionConflictException("Usuário já inscrito: " + user);
    }

    log.info("Usuário inscrito");
    Subscription res = subscriptionRepository.save(subs);

    return new SubscriptionResponse(
        res.getSubsNumber(),
        "http://codecraft.com/" + event.getPrettyName() + "/" + res.getSubscriber().getId());
  }

  // Falta implementar
  public List<SubscriptionRankingItem> getRanking() {
    return subscriptionRepository.generateRanking();
  }
}
