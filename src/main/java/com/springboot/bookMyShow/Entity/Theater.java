package com.springboot.bookMyShow.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Theater {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tId;;
	private String tName;
	private String tLocation;
	private  int tCapacity;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Shows tShows;
	@OneToMany(cascade = CascadeType.ALL)
	private Seats tSeats;
	

}
