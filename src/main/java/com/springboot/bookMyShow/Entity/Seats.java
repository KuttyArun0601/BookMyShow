package com.springboot.bookMyShow.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
	@NotNull(message = "name can't be not null")
	@NotBlank(message = "name can't be not blank")
	private String stype;
	@Positive(message = "no of seats can't be negative value")
	@Min(value = 1,message = "seats allowed minimum 1 ")
	@Max(value = 50,message = "seats allowed maximum 50 only")
	private int sNoOfSeats;
	
	@ManyToOne
	private Shows sShow;
}
