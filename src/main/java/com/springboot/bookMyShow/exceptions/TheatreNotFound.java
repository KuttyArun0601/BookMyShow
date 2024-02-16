package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class TheatreNotFound extends RuntimeException{
	
	String message;

	public TheatreNotFound(String message) {
		super();
		this.message = message;
	}
	
	

}
