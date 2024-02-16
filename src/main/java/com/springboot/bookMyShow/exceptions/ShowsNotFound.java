package com.springboot.bookMyShow.exceptions;

import lombok.Getter;

@Getter
public class ShowsNotFound extends RuntimeException{
	
	String message;

	public ShowsNotFound(String message) {
		super();
		this.message = message;
	}
	
	

}
