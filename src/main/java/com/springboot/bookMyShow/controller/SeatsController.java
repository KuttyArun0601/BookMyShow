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

import com.springboot.bookMyShow.Entity.Seats;
import com.springboot.bookMyShow.services.SeatsService;
import com.springboot.bookMyShow.util.ResponceStructure;

@RestController
@RequestMapping("seats")
public class SeatsController {

	@Autowired
	SeatsService sService;

	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Seats>> saveSeats(Seats seats)
	{
		return sService.saveSeats(seats);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Seats>> findSeats(int sId)
	{
		return sService.findSeats(sId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Seats>> delelteSeats(int sId)
	{
		return sService.deleteSeats(sId);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Seats>> updateSeats(Seats seats, int sId)
	{
		return sService.updateSeats(seats, sId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Seats>>> findAllSeats()
	{
		return sService.findAllSeats();
	}
}
