package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class AdminNotFound extends RuntimeException{

	String message;

	public AdminNotFound(String message) {
		
		this.message = message;
	}
	
	
	
}
