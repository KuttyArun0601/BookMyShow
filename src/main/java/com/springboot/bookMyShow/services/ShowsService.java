package com.springboot.bookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.dao.ShowsDao;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class ShowsService {
	
	@Autowired
	ShowsDao sDao;
	
	public ResponseEntity<ResponceStructure<Shows>> saveShows(Shows shows)
	{
		ResponceStructure<Shows> str=new ResponceStructure<Shows>();
		
		str.setMessage(" Show has added");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(sDao.saveShows(shows));
		
		return new ResponseEntity<ResponceStructure<Shows>>(str,HttpStatus.CREATED);
		
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
		return null;
	}
	
	public ResponseEntity<ResponceStructure<Shows>> deleteShows(int sId)
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
		return null;
		
	}
	
	public ResponseEntity<ResponceStructure<Shows>> updateShows(Shows shows,int sId)
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
		return null;
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
		return null;
	}

	
	
}
