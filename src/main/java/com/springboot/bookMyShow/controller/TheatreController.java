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

import com.springboot.bookMyShow.Entity.Theatre;
import com.springboot.bookMyShow.services.TheatreService;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("theatre")
public class TheatreController {

	@Autowired
	TheatreService tService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Theatre>> saveTheatre(@Valid @RequestBody Theatre theatre,BindingResult result ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return tService.saveTheatre(theatre, aEmail, aPassword);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Theatre>> findTheatre(int tId)
	{
		return tService.findTheatre(tId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Theatre>> delelteTheatre(@RequestParam int tId ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return tService.deleteTheatre(tId, aEmail, aPassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Theatre>> updateShows(@RequestBody Theatre theatre,@RequestParam int tId ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return tService.updateTheatre(theatre, tId, aEmail, aPassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Theatre>>> findAllShows()
	{
		return tService.findAllTheatre();
	}
	

}
