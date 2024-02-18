package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.bookMyShow.Entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

	
	
}
