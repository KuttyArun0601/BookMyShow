package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.Movie;
import com.springboot.bookMyShow.repo.MovieRepo;

@Repository
public class MovieDao {

	
	@Autowired
	MovieRepo mRepo;
	
	public Movie saveMovie(Movie movie)
	{
		return mRepo.save(movie);
	}
	
	public Movie findMovie(int mId)
	{
		Optional<Movie> opMovie=mRepo.findById(mId);
		if(opMovie.isPresent())
		{
			return opMovie.get();
		}
		return null;
	}
	
	public Movie deleteMovie(int mId)
	{
		Movie m=findMovie(mId);
		mRepo.delete(m);
		return m;
	
	}
	
	public Movie updateMovie(Movie movie, int mId)
	{
		Movie exm=findMovie(mId);
		
		if(exm!=null)
		{
			movie.setMId(mId);
			return mRepo.save(movie);
		}
		return null;
	}
	
	public List<Movie> findAllMovie()
	{
		List<Movie> movie=mRepo.findAll();
		return movie;
	}
	
	
}
