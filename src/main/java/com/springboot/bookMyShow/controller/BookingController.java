package com.springboot.bookMyShow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookMyShow.Entity.Booking;
import com.springboot.bookMyShow.services.BookingService;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("booking")
public class BookingController {

	@Autowired
	BookingService bService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Booking>> saveBooking(@Valid @RequestBody Booking booking,BindingResult result ,@RequestParam String uEmail, @RequestParam String uPassword)
	{
		return bService.saveBooking(booking, uEmail, uPassword);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Booking>> findBooking(int bId)
	{
		return bService.findBooking(bId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Booking>> delelteBooking(@RequestParam int bId,@RequestParam String uEmail, @RequestParam String uPassword)
	{
		return bService.deleteBooking(bId, uEmail, uPassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Booking>> updateBooking(@RequestBody Booking booking, @RequestParam int bId,@RequestParam String uEmail, @RequestParam String uPassword)
	{
		return bService.updateBooking(booking, bId, uEmail, uPassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Booking>>> findAllBooking()
	{
		return bService.findAllBooking();
	}
}
