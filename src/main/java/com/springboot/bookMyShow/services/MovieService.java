package com.springboot.bookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Movie;
import com.springboot.bookMyShow.dao.MovieDao;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class MovieService {

	@Autowired
	MovieDao mDao;
	
	public ResponseEntity<ResponceStructure<Movie>> saveMovie(Movie movie)
	{
		ResponceStructure<Movie> str=new ResponceStructure<Movie>();
		
		str.setMessage(movie.getMTitle()+" Movie has saved");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(mDao.saveMovie(movie));
		
		return new ResponseEntity<ResponceStructure<Movie>>(str,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponceStructure<Movie>> findMovie(int mId)
	{
		ResponceStructure<Movie> str=new ResponceStructure<Movie>();
		Movie m= mDao.findMovie(mId);
		if(m!=null)
		{
			str.setMessage(m.getMTitle()+"Movie has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(m);
			
			return new ResponseEntity<ResponceStructure<Movie>>(str,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponceStructure<Movie>> deleteMovie(int mId)
	{
		ResponceStructure<Movie> str= new ResponceStructure<Movie>();
		
		Movie m= mDao.findMovie(mId);
		
		if(m!=null)
		{
			str.setMessage(m.getMTitle()+"Movie has Deleted");
			str.setStatus(HttpStatus.OK.value());
			str.setData(mDao.deleteMovie(mId));
			
			return new ResponseEntity<ResponceStructure<Movie>>(str,HttpStatus.OK);
		}
		return null;
		
	}
	
	public ResponseEntity<ResponceStructure<Movie>> updateMovie(Movie movie,int mId)
	{
		ResponceStructure<Movie> str= new ResponceStructure<Movie>();
		
		Movie m=mDao.findMovie(mId);
		if(m!=null)
		{
			str.setMessage(movie.getMTitle()+" movie has updated");
			str.setStatus(HttpStatus.OK.value());
			str.setData(mDao.updateMovie(movie, mId));
			
			return new ResponseEntity<ResponceStructure<Movie>>(str, HttpStatus.OK);
		}
		return null;
	}
	
	public ResponseEntity<ResponceStructure<List<Movie>>> findAllMovies()
	{
		List<Movie> mList=mDao.findAllMovie();
		ResponceStructure<List<Movie>> str= new ResponceStructure<List<Movie>>();
		
		if(mList!=null)
		{
			str.setMessage("All movies are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(mList);
			
			return new ResponseEntity<ResponceStructure<List<Movie>>>(str,HttpStatus.FOUND);
			
		}
		return null;
	}
	
}
