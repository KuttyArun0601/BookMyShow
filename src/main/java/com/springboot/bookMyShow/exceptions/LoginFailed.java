package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class LoginFailed extends RuntimeException {

	String message;

	public LoginFailed(String message) {
		
		this.message = message;
	}
	
	
}
