package com.springboot.bookMyShow.Entity;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull(message = "show name can't be null")
	@NotBlank(message = "show name can't be blank")
	private String sName;
	private LocalTime sStartTime;
	private LocalTime sEndTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Movie sMovie;
	
	@OneToMany(cascade =CascadeType.ALL)
	private List<Seats>  sSeats;
	
	@ManyToOne
	private Theatre sTheatre;

}
