package com.springboot.bookMyShow.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Component
public class Seats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	private int sNoOfSeats;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Shows sShow;
}
