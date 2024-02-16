package com.springboot.bookMyShow.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
	@NotNull(message = "name can't be not null")
	@NotBlank(message = "name can't be not blank")
	private String aName;
	@Positive(message = "contact can't be negative value")
	@Min(value = 6000000000l,message = "number must start above 6*10 ")
	@Max(value = 9999999999l,message = "number must end before 10*10 ")
	private long aContact;
	@NotNull(message = "email can't be not null")
	@NotBlank(message = "email can't be not blank")
	@Email(message = "enter valid email (ex: abc@gmmail.com)")
	private String aEmail;
	@NotNull(message = "password can't be not null")
	@NotBlank(message = "password can't be not blank")
	@Size(min = 8 , message = "password must be in minimum 8 charecter ")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
		message = "Your password must have at least minimum 8 charecter, 1 digit, 1 uppercase, 1 lowercase & 1 special charecter")
	private String aPassword;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> aUser;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> aTheatre;
	
}
