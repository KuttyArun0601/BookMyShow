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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
	@NotNull(message = "name can't be not null")
	@NotBlank(message = "name can't be not blank")
	private TheatreName tName;
	@NotNull(message = "location can't be not null")
	@NotBlank(message = "location can't be not blank")
	private String tLocation;
	@Positive(message = "no of seats can't be negative value")
	@Min(value = 1,message = "capacity allowed minimum 1 ")
	@Max(value = 50,message = "capacity allowed maximum 50 only")
	private  int tCapacity;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Shows> tShows;
	

}
