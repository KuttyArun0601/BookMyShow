package com.springboot.bookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.Entity.Movie;
import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.dao.AdminDao;
import com.springboot.bookMyShow.dao.MovieDao;
import com.springboot.bookMyShow.dao.ShowsDao;
import com.springboot.bookMyShow.dao.TheatreDao;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.exceptions.ShowsNotFound;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class ShowsService {
	
	@Autowired
	ShowsDao sDao;
	
	@Autowired
	AdminDao aDao;
	
	@Autowired
	TheatreDao tDao;
	
	@Autowired
	MovieDao mDao;
	
	public ResponseEntity<ResponceStructure<Shows>> saveShows(Shows shows ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Shows> str=new ResponceStructure<Shows>();
			shows.setSName(shows.getSName().toLowerCase());
			str.setMessage(" Show has added");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(sDao.saveShows(shows));
			
			return new ResponseEntity<ResponceStructure<Shows>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Shows>> findShows(int sId)
	{
		ResponceStructure<Shows> str=new ResponceStructure<Shows>();
		Shows s= sDao.findShows(sId);
		if(s!=null)
		{
			str.setMessage("Shows has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(s);
			
			return new ResponseEntity<ResponceStructure<Shows>>(str,HttpStatus.FOUND);
		}
		throw new ShowsNotFound("Shows not found with the given id"+sId);
	}
	
	public ResponseEntity<ResponceStructure<Shows>> deleteShows(int sId ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Shows> str= new ResponceStructure<Shows>();
			
			Shows s= sDao.findShows(sId);
			
			if(s!=null)
			{
				str.setMessage("Seat has Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.deleteShows(sId));
				
				return new ResponseEntity<ResponceStructure<Shows>>(str,HttpStatus.OK);
			}
			throw new ShowsNotFound("Shows not found with the given id"+sId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Shows>> updateShows(Shows shows,int sId ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Shows> str= new ResponceStructure<Shows>();
			
			Shows s=sDao.findShows(sId);
			if(s!=null)
			{
				str.setMessage(" Seat has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.updateShows(shows, sId));
				
				return new ResponseEntity<ResponceStructure<Shows>>(str, HttpStatus.OK);
			}
			throw new ShowsNotFound("Shows not found with the given id"+sId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<List<Shows>>> findAllShows()
	{
		List<Shows> sList=sDao.findAllShows();
		ResponceStructure<List<Shows>> str= new ResponceStructure<List<Shows>>();
		
		if(sList!=null)
		{
			str.setMessage("All seats are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(sList);
			
			return new ResponseEntity<ResponceStructure<List<Shows>>>(str,HttpStatus.FOUND);
			
		}
		throw new ShowsNotFound("Shows not found");
	}
	
	public ResponseEntity<ResponceStructure<Shows>> assignMovieToShow(int mId, int sId, String aEmail, String aPassword)
	{
		Admin exa = aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Shows> str= new ResponceStructure<Shows>();
			Shows exs=sDao.findShows(sId);
			Movie exm=mDao.findMovie(mId);
			exs.setSMovie(exm);
			
			str.setMessage("movie has assigned to show");
			str.setStatus(HttpStatus.OK.value());
			str.setData(sDao.updateShows(exs, sId));
			
			return new ResponseEntity<ResponceStructure<Shows>>(str,HttpStatus.OK);
			
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Shows>> deleteMovieFromShow(int mId, int sId ,String aEmail,String aPassword)
	{
		Admin exa = aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Shows> str= new ResponceStructure<Shows>();
			Shows exs=sDao.findShows(sId);
			if(exs!=null && exs.getSMovie().getMId()==mId)
			{
				exs.setSMovie(null);
				mDao.deleteMovie(mId);
				
				str.setMessage("Movie deleted from show");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.updateShows(exs, sId));
				
				return new ResponseEntity<ResponceStructure<Shows>>(str,HttpStatus.OK);
			}
			throw new ShowsNotFound("Show not found with the given id (or) not match with the movie id");
			
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Shows>> removeMovieFromShow(int mId, int sId ,String aEmail,String aPassword)
	{
		Admin exa = aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Shows> str= new ResponceStructure<Shows>();
			Shows exs=sDao.findShows(sId);
			if(exs!=null && exs.getSMovie().getMId()==mId)
			{
				exs.setSMovie(null);
				
				str.setMessage("Movie deleted from show");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.updateShows(exs, sId));
				
				return new ResponseEntity<ResponceStructure<Shows>>(str,HttpStatus.OK);
			}
			throw new ShowsNotFound("Show not found with the given id (or) not match with the movie id");
			
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	
	
}
