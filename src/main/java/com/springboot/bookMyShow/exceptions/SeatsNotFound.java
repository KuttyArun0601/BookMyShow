package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class SeatsNotFound extends RuntimeException{

	String message;

	public SeatsNotFound(String message) {
		super();
		this.message = message;
	}
	
	
}
