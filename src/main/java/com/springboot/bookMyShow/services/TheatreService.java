package com.springboot.bookMyShow.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.Entity.Theatre;
import com.springboot.bookMyShow.dao.AdminDao;
import com.springboot.bookMyShow.dao.ShowsDao;
import com.springboot.bookMyShow.dao.TheatreDao;

import com.springboot.bookMyShow.exceptions.AdminNotFound;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.exceptions.ShowsNotFound;
import com.springboot.bookMyShow.exceptions.TheatreNotFound;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class TheatreService {

	@Autowired
	TheatreDao tDao;
	
	@Autowired
	AdminDao aDao;
	
	@Autowired
	ShowsDao sDao;
	
	public ResponseEntity<ResponceStructure<Theatre>> saveTheatre(Theatre theatre ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Theatre> str=new ResponceStructure<Theatre>();
			
			str.setMessage("Theatre has saved");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(tDao.saveTheatre(theatre));
			
			return new ResponseEntity<ResponceStructure<Theatre>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Theatre>> findTheatre(int tId)
	{
		
		ResponceStructure<Theatre> str=new ResponceStructure<Theatre>();
		Theatre t= tDao.findTheatre(tId);
		if(t!=null)
		{
			str.setMessage("Theatre has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(t);
			
			return new ResponseEntity<ResponceStructure<Theatre>>(str,HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatre not found with the given id"+tId);
		
	}
	
	public ResponseEntity<ResponceStructure<Theatre>> deleteTheatre(int tId ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Theatre> str= new ResponceStructure<Theatre>();
			
			Theatre t= tDao.findTheatre(tId);
			
			if(t!=null)
			{
				str.setMessage("Theatre has Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.deleteTheatre(tId));
				
				return new ResponseEntity<ResponceStructure<Theatre>>(str,HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id"+tId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Theatre>> updateTheatre(Theatre theatre,int tId ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Theatre> str= new ResponceStructure<Theatre>();
			
			Theatre t=tDao.findTheatre(tId);
			if(t!=null)
			{
				str.setMessage(" Theatre has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.updatetheatre(theatre, tId));
				
				return new ResponseEntity<ResponceStructure<Theatre>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id"+tId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
	}
	
	public ResponseEntity<ResponceStructure<List<Theatre>>> findAllTheatre()
	{
		List<Theatre> tList=tDao.findAllTheatre();
		ResponceStructure<List<Theatre>> str= new ResponceStructure<List<Theatre>>();
		
		if(tList!=null)
		{
			str.setMessage("All Theatre are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(tList);
			
			return new ResponseEntity<ResponceStructure<List<Theatre>>>(str,HttpStatus.FOUND);
			
		}
		throw new TheatreNotFound("Theatre not found ");
	}
	
	public ResponseEntity<ResponceStructure<Theatre>> assignShowToTheatre(int tId,int sId,String aEmail, String aPassword)
	{
		Admin va=aDao.adminLogin(aEmail, aPassword);
		if(va!=null)
		{
			Theatre t= tDao.findTheatre(tId);
			Shows s=sDao.findShows(sId);
			List<Shows> sList=t.getTShows();
			ResponceStructure<Theatre> str= new ResponceStructure<Theatre>();
			if (t!=null && s != null) {
				sList.add(s);
				t.setTShows(sList);
				
				str.setMessage("show assigned to the Theatre");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.updatetheatre(t, tId));
				return new ResponseEntity<ResponceStructure<Theatre>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("show not found with the given id");
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<Theatre>> deleteShowFromTheatre(int tId, int sId, String aEmail, String aPassword)
	{
		Admin a =aDao.adminLogin(aEmail, aPassword);
		if(a!=null)
		{
			Shows show=sDao.findShows(sId);
			Theatre t=tDao.findTheatre(tId);
			List<Shows> sList=t.getTShows();
			ResponceStructure<Theatre> str=new ResponceStructure<Theatre>();
			for (Shows s : sList) {
				if(s.getSId()==sId)
				{
					sList.remove(show);
					t.setTShows(sList);
					sDao.deleteShows(sId);
					str.setMessage("removed");
					str.setStatus(HttpStatus.OK.value());
					str.setData(tDao.updatetheatre(t, tId));
					
					return new ResponseEntity<ResponceStructure<Theatre>>(str,HttpStatus.OK);
				}
				throw new ShowsNotFound("Shows not found with the given id");
			}
			throw new ShowsNotFound("Shows not found with the given id");
		}
		throw new AdminNotFound("Enter valid email & passworrd");
		
 	}
	public ResponseEntity<ResponceStructure<Theatre>> removeShowFromTheatre(int tId, int sId, String aEmail, String aPassword)
	{
		Admin a =aDao.adminLogin(aEmail, aPassword);
		if(a!=null)
		{
			Shows show=sDao.findShows(sId);
			Theatre t=tDao.findTheatre(tId);
			List<Shows> sList=t.getTShows();
			ResponceStructure<Theatre> str=new ResponceStructure<Theatre>();
			for (Shows s : sList) {
				if(s.getSId()==sId)
				{
					sList.remove(show);
					t.setTShows(sList);
					str.setMessage("removed");
					str.setStatus(HttpStatus.OK.value());
					str.setData(tDao.updatetheatre(t, tId));
					
					return new ResponseEntity<ResponceStructure<Theatre>>(str,HttpStatus.OK);
				}
				throw new ShowsNotFound("Shows not found with the given id");
			}
			throw new ShowsNotFound("Shows not found with the given id");
		}
		throw new AdminNotFound("Enter valid email & passworrd");
		
 	}
	
}
