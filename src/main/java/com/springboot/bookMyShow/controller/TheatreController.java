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

import com.springboot.bookMyShow.Entity.Theatre;
import com.springboot.bookMyShow.services.TheatreService;
import com.springboot.bookMyShow.util.ResponceStructure;

@RestController
@RequestMapping("theatre")
public class TheatreController {

	@Autowired
	TheatreService tService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Theatre>> saveTheatre(Theatre theatre)
	{
		return tService.saveTheatre(theatre);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Theatre>> findTheatre(int tId)
	{
		return tService.findTheatre(tId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Theatre>> delelteTheatre(int tId)
	{
		return tService.deleteTheatre(tId);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Theatre>> updateShows(Theatre theatre, int tId)
	{
		return tService.updateTheatre(theatre, tId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Theatre>>> findAllShows()
	{
		return tService.findAllTheatre();
	}
	

}
