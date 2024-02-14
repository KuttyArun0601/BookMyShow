package com.springboot.bookMyShow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookMyShow.Entity.Booking;
import com.springboot.bookMyShow.services.BookingService;
import com.springboot.bookMyShow.util.ResponceStructure;

@RestController
@RequestMapping("booking")
public class BookingController {

	@Autowired
	BookingService bService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Booking>> saveBooking(Booking booking)
	{
		return bService.saveBooking(booking);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Booking>> findBooking(int bId)
	{
		return bService.findBooking(bId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Booking>> delelteBooking(int bId)
	{
		return bService.deleteBooking(bId);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Booking>> updateBooking(Booking booking, int bId)
	{
		return bService.updateBooking(booking, bId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Booking>>> findAllBooking()
	{
		return bService.findAllBoiking();
	}
}
