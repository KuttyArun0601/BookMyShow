package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class TicketNotFound extends RuntimeException {

	String message;

	public TicketNotFound(String message) {
		
		this.message = message;
	}
	
	
}
