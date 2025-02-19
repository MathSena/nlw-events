package com.mathsena.events.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="event_id")
	private Integer eventId;
	@Column(name="title", length = 255, nullable = false)
	private String title;
	
	@Column(name="pretty_name", length = 50, nullable = false, unique=true)
	private String prettyName;
	
	@Column(name="location", length = 50, nullable = false)
	private String location;
	
	@Column(name="price", length = 50, nullable = false)
	private Double price;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="start_time")
	private LocalDate startTime;
	
	@Column(name="end_time")
	private LocalDate endTime;
	

}
