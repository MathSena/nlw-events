package com.mathsena.events.controller;

import com.mathsena.events.model.Event;
import com.mathsena.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

  @Autowired private EventService service;

  @PostMapping("/events")
  public Event addEvent(@RequestBody Event event) {
    return service.addEvent(event);
  }

  @GetMapping("/events")
  public List<Event> getAllEvents() {
    return service.getAllEvents();
  }

  @GetMapping("/events/{prettyName}")
  public Event getEventByName(@PathVariable String prettyName) {
    return service.getByPrettyName(prettyName);
  }
}
