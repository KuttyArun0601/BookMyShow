package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class MovieNotFound extends RuntimeException{

	String message;

	public MovieNotFound(String message) {
		super();
		this.message = message;
	}
	
	
}
