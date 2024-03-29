package com.springboot.bookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.Entity.Seats;
import com.springboot.bookMyShow.dao.AdminDao;
import com.springboot.bookMyShow.dao.SeatsDao;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.exceptions.SeatsNotFound;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class SeatsService {

	@Autowired
	SeatsDao sDao;
	
	@Autowired
	AdminDao aDao;
	
	public ResponseEntity<ResponceStructure<Seats>> saveSeats(Seats seats ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Seats> str=new ResponceStructure<Seats>();
			
			str.setMessage(" seat has added");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(sDao.saveSeats(seats));
			
			return new ResponseEntity<ResponceStructure<Seats>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Seats>> findSeats(int sId )
	{
		ResponceStructure<Seats> str=new ResponceStructure<Seats>();
		Seats s= sDao.findSeats(sId);
		if(s!=null)
		{
			str.setMessage("Seats has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(s);
			
			return new ResponseEntity<ResponceStructure<Seats>>(str,HttpStatus.FOUND);
		}
		throw new SeatsNotFound("Seats not found with the given id"+sId);
	}
	
	public ResponseEntity<ResponceStructure<Seats>> deleteSeats(int sId ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Seats> str= new ResponceStructure<Seats>();
			
			Seats s= sDao.findSeats(sId);
			
			if(s!=null)
			{
				str.setMessage("Seat has Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.deleteSeats(sId));
				
				return new ResponseEntity<ResponceStructure<Seats>>(str,HttpStatus.OK);
			}
			throw new SeatsNotFound("Seats not found with the given id"+sId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponceStructure<Seats>> updateSeats(Seats seats,int sId ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<Seats> str= new ResponceStructure<Seats>();
			
			Seats s=sDao.findSeats(sId);
			if(s!=null)
			{
				str.setMessage(" Seat has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.updateSeats(seats, sId));
				
				return new ResponseEntity<ResponceStructure<Seats>>(str, HttpStatus.OK);
			}
			throw new SeatsNotFound("Seats not found with the given id"+sId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
	}
	
	public ResponseEntity<ResponceStructure<List<Seats>>> findAllSeats()
	{
		List<Seats> sList=sDao.findAllSeats();
		ResponceStructure<List<Seats>> str= new ResponceStructure<List<Seats>>();
		
		if(sList!=null)
		{
			str.setMessage("All seats are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(sList);
			
			return new ResponseEntity<ResponceStructure<List<Seats>>>(str,HttpStatus.FOUND);
			
		}
		throw new SeatsNotFound("Seats not found");
	}
}
