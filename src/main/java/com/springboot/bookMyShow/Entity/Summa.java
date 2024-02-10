package com.springboot.bookMyShow.Entity;


import java.time.LocalTime;

public class Summa {
	
	public LocalTime duration;

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime localTime) {
		this.duration = localTime;
	}
	
	public static void main(String[] args) {
		
		Summa s= new Summa();
		s.setDuration(LocalTime.of(9, 30, 30));
		
		System.out.println(s.duration);
	}

}
