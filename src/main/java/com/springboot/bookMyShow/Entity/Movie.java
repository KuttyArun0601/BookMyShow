package com.springboot.bookMyShow.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mId;
	@NotNull(message = "title can't be not null")
	@NotBlank(message = "title can't be not blank")
	private String mTitle;
	@NotNull(message = "genre can't be not null")
	@NotBlank(message = "genre can't be not blank")
	private String mGenre;
	@Positive(message = "contact can't be negative value")
	@Min(value = 3,message = "ratting must start above 3 ")
	@Max(value = 10,message = "ratting must end before 10 ")
	private double mRatting;
	private double mprice;
	private LocalTime mduration;
	private LocalDate mDate;
	
	@OneToOne
	private Shows mShow;
}
