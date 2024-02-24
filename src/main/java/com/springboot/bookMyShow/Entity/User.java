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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@NotNull(message = "name can't be  null")
	@NotBlank(message = "name can't be  blank")
	private String uName;
	@NotNull(message = "email can't be  null")
	@Email(message = "enter valid email (ex: abc@gmail.com)")
	@NotBlank(message = "email can't be  blank")
	private String uEmail;
	@NotNull(message = "password can't be  null")
	@NotBlank(message = "password can't be not blank")
	@Size(min = 8 , message = "password must be in minimum 8 charecter ")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
	message = "Your password must have at least minimum 8 charecter, 1 digit, 1 uppercase, 1 lowercase & 1 special charecter")
	private String uPassword;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Booking> uBooking;
}
