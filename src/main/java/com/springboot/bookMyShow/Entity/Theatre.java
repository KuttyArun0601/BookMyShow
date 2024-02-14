package com.springboot.bookMyShow.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.bookMyShow.bEnum.TheatreName;

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
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tId;;
	private TheatreName tName;
	private String tLocation;
	private  int tCapacity;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Shows> tShows;
	

}
