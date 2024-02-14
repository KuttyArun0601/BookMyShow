package com.springboot.bookMyShow.Entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Component
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bId;
	private int bNoOfTicket;
	private double bprice;
	private LocalDate bDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> bTicket;

	@ManyToOne(cascade = CascadeType.ALL)
	private User bUser;
	
	
	
}
