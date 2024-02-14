package com.springboot.bookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Booking;
import com.springboot.bookMyShow.dao.BookingDao;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class BookingService {

	@Autowired
	BookingDao  bDao;
	
	public ResponseEntity<ResponceStructure<Booking>> saveBooking(Booking booking)
	{
		ResponceStructure<Booking> str = new ResponceStructure<Booking>();
		
		str.setMessage("booking has done");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(bDao.saveBooking(booking));
		
		return new ResponseEntity<ResponceStructure<Booking>>(str,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponceStructure<Booking>> findBooking(int bId)
	{
		ResponceStructure<Booking> str= new ResponceStructure<Booking>();
		
		Booking b=bDao.findBooking(bId);
		if(b!=null)
		{
			str.setMessage("Booking has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(b);
			return new ResponseEntity<ResponceStructure<Booking>>(str,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<ResponceStructure<Booking>> deleteBooking(int bId)
	{
		ResponceStructure<Booking> str= new ResponceStructure<Booking>();
		
		Booking b= bDao.findBooking(bId);
		if(b!=null)
		{
			str.setMessage("Booking has Deleted");
			str.setStatus(HttpStatus.OK.value());
			str.setData(bDao.deleteBooking(bId));
			
			return new ResponseEntity<ResponceStructure<Booking>>(str,HttpStatus.OK);
		}
		return null;
	}
	
	public ResponseEntity<ResponceStructure<Booking>> updateBooking(Booking booking,int bId)
	{
		ResponceStructure<Booking> str=new  ResponceStructure<Booking>();
		
		Booking b=bDao.findBooking(bId);
		if(b!=null)
		{
			str.setMessage("Booking has updated");
			str.setStatus(HttpStatus.OK.value());
			str.setData(bDao.updateBooking(booking, bId));
			
			return new ResponseEntity<ResponceStructure<Booking>>(str, HttpStatus.OK);
		}
		return null;
		
	}
	
	public ResponseEntity<ResponceStructure<List<Booking>>> findAllBoiking()
	{
		ResponceStructure<List<Booking>> str=new ResponceStructure<List<Booking>>();
		List<Booking> bList=bDao.findAllBooking();
		
		if(bList!=null)
		{
			str.setMessage("All Bookings are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(bList);
			
			return new ResponseEntity<ResponceStructure<List<Booking>>>(str,HttpStatus.FOUND);
		}
		return null;
	}
	
}
