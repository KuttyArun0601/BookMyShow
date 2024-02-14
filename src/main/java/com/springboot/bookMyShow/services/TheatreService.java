package com.springboot.bookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Theatre;
import com.springboot.bookMyShow.dao.TheatreDao;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class TheatreService {

	@Autowired
	TheatreDao tDao;
	
	public ResponseEntity<ResponceStructure<Theatre>> saveTheatre(Theatre theatre)
	{
		ResponceStructure<Theatre> str=new ResponceStructure<Theatre>();
		
		str.setMessage("Theatre has saved");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(tDao.saveTheatre(theatre));
		
		return new ResponseEntity<ResponceStructure<Theatre>>(str,HttpStatus.CREATED);
		
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
		return null;
	}
	
	public ResponseEntity<ResponceStructure<Theatre>> deleteTheatre(int tId)
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
		return null;
		
	}
	
	public ResponseEntity<ResponceStructure<Theatre>> updateTheatre(Theatre theatre,int tId)
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
		return null;
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
		return null;
	}
	
}
