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
@Component
@Getter
@Setter
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aId;
	private String aName;
	private long aContact;
	private String aEmail;
	private String aPassword;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> aUser;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> aTheatre;
	
}
