package com.springboot.bookMyShow.dto;

import java.util.List;

import com.springboot.bookMyShow.Entity.Booking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private int uId;
	private String uName;
	private String uEmail;

	private List<Booking> uBooking;
}
