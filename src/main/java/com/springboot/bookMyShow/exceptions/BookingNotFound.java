package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class BookingNotFound extends RuntimeException {

	String message;

	public BookingNotFound(String message) {
		
		this.message = message;
	}
	
	
}
