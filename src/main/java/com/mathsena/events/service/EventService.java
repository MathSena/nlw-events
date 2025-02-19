package com.mathsena.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathsena.events.model.Event;
import com.mathsena.events.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

  @Autowired private EventRepository repository;

  public Event addEvent(Event event) {
    event.setPrettyName(event.getPrettyName().toLowerCase().replaceAll("", "-"));
    return repository.save(event);
  }
  ;

  public List<Event> getAllEvents() {
    return (List<Event>) repository.findAll();
  }

  public Event getByPrettyName(String prettyName) {
    return repository.findByPrettyName(prettyName);
  }
}
