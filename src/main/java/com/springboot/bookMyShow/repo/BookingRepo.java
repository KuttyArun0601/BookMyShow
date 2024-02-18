package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.bookMyShow.Entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{
//
//	@Query("select b from Booking b where b.bTitle=?1")
//	public 
	
}
