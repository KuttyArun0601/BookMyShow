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

import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.services.ShowsService;
import com.springboot.bookMyShow.util.ResponceStructure;

@RestController
@RequestMapping("shows")
public class ShowsController {
	
	
	@Autowired
	ShowsService sService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Shows>> saveShows(Shows shows)
	{
		return sService.saveShows(shows);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Shows>> findShows(int sId)
	{
		return sService.findShows(sId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Shows>> delelteShows(int sId)
	{
		return sService.deleteShows(sId);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Shows>> updateShows(Shows shows, int sId)
	{
		return sService.updateShows(shows, sId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Shows>>> findAllShows()
	{
		return sService.findAllShows();
	}
	


}
