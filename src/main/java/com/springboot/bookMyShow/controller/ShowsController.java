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

import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.services.ShowsService;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("shows")
public class ShowsController {
	
	
	@Autowired
	ShowsService sService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Shows>> saveShows(@Valid @RequestBody Shows shows,BindingResult result ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.saveShows(shows, aEmail, aPassword);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Shows>> findShows(@RequestParam int sId)
	{
		return sService.findShows(sId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Shows>> delelteShows(@RequestParam int sId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.deleteShows(sId, aEmail, aPassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Shows>> updateShows(@RequestBody Shows shows, @RequestParam int sId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.updateShows(shows, sId, aEmail, aPassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Shows>>> findAllShows()
	{
		return sService.findAllShows();
	}
	
	@PutMapping("assignMovie")
	public ResponseEntity<ResponceStructure<Shows>> assignMovieToShow(@RequestParam int mId,@RequestParam int sId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.assignMovieToShow(mId, sId, aEmail, aPassword);
	}
	@PutMapping("deleteMovie")
	public ResponseEntity<ResponceStructure<Shows>> deleteMovieFromShow(@RequestParam int mId,@RequestParam int sId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.deleteMovieFromShow(mId, sId, aEmail, aPassword);
	}
	@PutMapping("removeMovie")
	public ResponseEntity<ResponceStructure<Shows>> removeMovieFromShow(@RequestParam int mId,@RequestParam int sId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return sService.removeMovieFromShow(mId, sId, aEmail, aPassword);
	}


}
