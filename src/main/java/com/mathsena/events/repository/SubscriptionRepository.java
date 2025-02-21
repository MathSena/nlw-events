package com.mathsena.events.repository;

import com.mathsena.events.dto.SubscriptionRankingItem;
import com.mathsena.events.model.Event;
import com.mathsena.events.model.Subscription;
import com.mathsena.events.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

  public Subscription findByEventAndSubscriber(Event event, User user);

  @Query(
      "select count(*) as subscribers, s.subscriber.id as userId, s.subscriber.name as name from Subscription s group by s.subscriber.id order by subscribers desc")
  public List<SubscriptionRankingItem> generateRanking();
}
