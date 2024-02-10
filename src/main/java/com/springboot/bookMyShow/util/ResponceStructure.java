package com.springboot.bookMyShow.util;


import lombok.Data;

@Data
public class ResponceStructure<T> {
	private String message;
	private int status;
	private T data;

}
