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

import com.springboot.bookMyShow.Entity.Seats;
import com.springboot.bookMyShow.services.SeatsService;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("seats")
public class SeatsController {

	@Autowired
	SeatsService sService;

	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Seats>> saveSeats(@Valid @RequestBody Seats seats,BindingResult result ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.saveSeats(seats, aEmail, aPassword);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Seats>> findSeats(int sId)
	{
		return sService.findSeats(sId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Seats>> delelteSeats(@RequestParam int sId ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.deleteSeats(sId, aEmail, aPassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Seats>> updateSeats(@RequestBody Seats seats, @RequestParam int sId ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.updateSeats(seats, sId, aEmail, aPassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Seats>>> findAllSeats()
	{
		return sService.findAllSeats();
	}
}
