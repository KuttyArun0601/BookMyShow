package com.springboot.bookMyShow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.Entity.Ticket;
import com.springboot.bookMyShow.Entity.User;
import com.springboot.bookMyShow.dao.ShowsDao;
import com.springboot.bookMyShow.dao.TicketDao;
import com.springboot.bookMyShow.dao.UserDao;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.exceptions.TheatreNotFound;
import com.springboot.bookMyShow.exceptions.TicketNotFound;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class TicketService {

	@Autowired
	TicketDao tDao;
	
	@Autowired
	UserDao uDao;
	
	@Autowired
	ShowsDao sDao;
	
	public ResponseEntity<ResponceStructure<Ticket>> saveTicket(Ticket ticket, String uEmail, String uPassword)
	{
		User exu=uDao.userLogin(uEmail, uPassword);
		if(exu!=null)
		{
			ResponceStructure<Ticket> str=new ResponceStructure<Ticket>();
			
			str.setMessage("");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(ticket);
			
			return new ResponseEntity<ResponceStructure<Ticket>>(str, HttpStatus.CREATED);
			
			
		}
		throw new LoginFailed("Enter valid email & passworrd");
	}
	
	public ResponseEntity<ResponceStructure<Ticket>> findTicket(int tId)
	{
		
		ResponceStructure<Ticket> str=new ResponceStructure<Ticket>();
		Ticket t=tDao.findTicket(tId);
		if(t!=null)
		{
			str.setMessage("Ticket has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(t);
			
			return new ResponseEntity<ResponceStructure<Ticket>>(str,HttpStatus.FOUND);
		}
		throw new TicketNotFound("Ticket not found with the given id"+tId);
		
	}
	
	public ResponseEntity<ResponceStructure<Ticket>> deleteTicket(int tId, String uEmail,String uPassword)
	{
		User exu=uDao.userLogin(uEmail, uPassword);
		if(exu!=null)
		{
			ResponceStructure<Ticket> str=new ResponceStructure<Ticket>();
			Ticket t=tDao.findTicket(tId);
			if(t!=null)
			{
				str.setMessage("ticket has deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.deleteTicket(tId));
				
				return new ResponseEntity<ResponceStructure<Ticket>>(str,HttpStatus.OK);
			}
			throw new TicketNotFound("Ticket not found with the given id"+tId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
	}
	
	public ResponseEntity<ResponceStructure<Ticket>> updateTicket(Ticket ticket ,int tId, String uEmail,String uPassword)
	{
		User exu=uDao.userLogin(uEmail, uPassword);
		if(exu!=null)
		{
			ResponceStructure<Ticket> str=new ResponceStructure<Ticket>();
			if(ticket!=null)
			{
				str.setMessage("ticket has deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.updateTicket(ticket, tId));
				
				return new ResponseEntity<ResponceStructure<Ticket>>(str,HttpStatus.OK);
			}
			throw new TicketNotFound("Ticket not found with the given id"+tId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
	}
	
	public ResponseEntity<ResponceStructure<List<Ticket>>> findAllTicket()
	{
		ResponceStructure<List<Ticket>> str=new ResponceStructure<List<Ticket>>();
		List<Ticket> tList=tDao.findAllTicket();
		
		if(tList!=null)
		{
			str.setMessage("");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(tList);
			
			return new ResponseEntity<ResponceStructure<List<Ticket>>>(str,HttpStatus.FOUND);
		}
		throw new TicketNotFound("Ticket not found ");
	}
	
	public ResponseEntity<ResponceStructure<Ticket>> findShowbyTicket(int tId)
	{
		ResponceStructure<Ticket> str= new ResponceStructure<Ticket>();
		Ticket t =tDao.findTicket(tId);
		Shows s= sDao.findShows(t.getTShows().getSId());
		if(s!=null)
		{
			str.setMessage(null);
			str.setStatus(HttpStatus.FOUND.value());
//			str.setData(s);
			
			return new ResponseEntity<ResponceStructure<Ticket>>(str, HttpStatus.FOUND);
		}
		
		
		return null;
	}
	
}
