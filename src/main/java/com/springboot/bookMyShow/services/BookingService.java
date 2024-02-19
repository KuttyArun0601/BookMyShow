package com.springboot.bookMyShow.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Booking;
import com.springboot.bookMyShow.Entity.Movie;
import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.Entity.User;
import com.springboot.bookMyShow.dao.BookingDao;
import com.springboot.bookMyShow.dao.MovieDao;
import com.springboot.bookMyShow.dao.SeatsDao;
import com.springboot.bookMyShow.dao.ShowsDao;
import com.springboot.bookMyShow.dao.UserDao;
import com.springboot.bookMyShow.exceptions.BookingNotFound;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.exceptions.ShowsNotFound;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class BookingService {

	@Autowired
	BookingDao  bDao;
	
	@Autowired
	UserDao uDao;
	
	@Autowired
	MovieDao mDao;
	
	@Autowired
	ShowsDao sDao;
	
	@Autowired
	SeatsDao seatDao;
	
	ModelMapper mapper=new ModelMapper();
	
	public ResponseEntity<ResponceStructure<Booking>> saveBooking(Booking booking,String uEmail,String uPassword)
	{

		User exu=uDao.userLogin(uEmail, uPassword);
		if(exu!=null)
		{
			ResponceStructure<Booking> str = new ResponceStructure<Booking>();
			int noOfTickets=booking.getBNoOfTicket();
			Movie exm=mDao.findmTitle(booking.getBMovieName().toLowerCase());
			
			booking.setBprice(exm.getMprice()*noOfTickets);
			booking.setBDate(exm.getMDate());
			booking.setBseats(noOfTickets);
			booking.setBShows(exm.getMShow());
			str.setMessage("booking has done");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(bDao.saveBooking(booking));
			
			return new ResponseEntity<ResponceStructure<Booking>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter the valid email & password");
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
		throw new BookingNotFound("booking not found with the given id"+bId);
	}
	
	public ResponseEntity<ResponceStructure<Booking>> deleteBooking(int bId,String uEmail,String uPassword)
	{
		User exu=uDao.userLogin(uEmail, uPassword);
		if(exu!=null)
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
			throw new BookingNotFound("booking not found with the given id"+bId);
		}
		throw new LoginFailed("Enter the valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<Booking>> updateBooking(Booking booking,int bId,String uEmail,String uPassword)
	{
		User exu=uDao.userLogin(uEmail, uPassword);
		if(exu!=null)
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
			throw new BookingNotFound("booking not found with the given id"+bId);
		}
		throw new LoginFailed("Enter the valid email & password");
		
	}
	
	public ResponseEntity<ResponceStructure<List<Booking>>> findAllBooking()
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
		throw new BookingNotFound("booking not found ");
	}
	
	public ResponseEntity<ResponceStructure<Booking>> assignShowToBooking(int bId,String uEmail,String uPassword)
	{
		ResponceStructure<Booking> str= new ResponceStructure<Booking>();
		
		
		Booking exb=bDao.findBooking(bId);
		Movie m= mDao.findmTitle(exb.getBMovieName());
		Shows exs=sDao.findShows(m.getMShow().getSId());
		
		if(exs!=null)
		{
			exb.setBShows(exs);
			str.setMessage("Show is assigned to booking");
			str.setStatus(HttpStatus.OK.value());
			str.setData(bDao.updateBooking(exb, bId));
			
			return new ResponseEntity<ResponceStructure<Booking>>(str,HttpStatus.OK);
		}
		throw new ShowsNotFound("Show not found with the given id");
		
	}
	
//	public ResponseEntity<ResponceStructure<Booking>> findUserByBooking(int bId,String uEmail,String uPassword)
//	{
//		User exu=uDao.userLogin(uEmail, uPassword);
//		if(exu!=null)
//		{
//			
//			ResponceStructure<Booking> str=new ResponceStructure<Booking>();
//			mapper.map(u, uDto);
//			
//		}
//		throw new LoginFailed("Enter valid email & password");
//	}
	
}
