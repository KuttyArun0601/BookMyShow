package com.springboot.bookMyShow.Entity;

import java.util.List;

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
@Getter
@Setter
@Component
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uId;
	private String uName;
	private String uEmail;
	private String uPassword;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Booking> uBooking;
}
