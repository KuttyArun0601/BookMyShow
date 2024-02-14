package com.springboot.bookMyShow.Entity;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	private String mTitle;
	private String mGenre;
	private double mRatting;
	private LocalTime duration;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Shows mShow;
}
