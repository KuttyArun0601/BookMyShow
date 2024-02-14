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

import com.springboot.bookMyShow.Entity.Movie;
import com.springboot.bookMyShow.services.MovieService;
import com.springboot.bookMyShow.util.ResponceStructure;

@RestController
@RequestMapping("movie")
public class MovieController {
	
	@Autowired
	MovieService mService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<Movie>> saveMovie(Movie movie)
	{
		return mService.saveMovie(movie);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<Movie>> findMovie(int mId)
	{
		return mService.findMovie(mId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<Movie>> delelteMovie(int mId)
	{
		return mService.deleteMovie(mId);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<Movie>> updateMovie(Movie movie, int mId)
	{
		return mService.updateMovie(movie, mId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<Movie>>> findAllUsers()
	{
		return mService.findAllMovies();
	}

}
