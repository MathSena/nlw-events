package com.mathsena.events.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subscription")
@Data
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer subsNumber;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event eventId;

  @ManyToOne
  @JoinColumn(name = "subscribed_user_id")
  private User subscriber;

  @ManyToOne
  @JoinColumn(name = "indication_user_id")
  private User indication;
}
