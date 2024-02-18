package com.springboot.bookMyShow.dto;

import java.util.List;

import com.springboot.bookMyShow.Entity.Theatre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
	
	private int aId;
	private String aName;
	private long aContact;
	private String aEmail;	
	
	private List<Theatre> aTheatre;
	
	private List<UserDto> aUser; 
}
