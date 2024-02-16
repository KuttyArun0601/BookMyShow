package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class UserNotFound extends RuntimeException{
	
	String message;

	public UserNotFound(String message) {
		
		this.message = message;
	}
	
	

}
