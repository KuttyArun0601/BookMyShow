package com.springboot.bookMyShow.Entity;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Component
public class Shows 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	private LocalTime sStartTime;
	private LocalTime sEndTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Movie sMovie;
	
	@OneToMany(cascade =CascadeType.ALL)
	private Seats  sSeats;

}
