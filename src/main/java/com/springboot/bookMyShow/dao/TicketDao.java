package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.Ticket;
import com.springboot.bookMyShow.repo.TicketRepo;

@Repository
public class TicketDao {
	
	@Autowired
	TicketRepo tRepo;
	
	public Ticket saveTicket(Ticket Ticket)
	{
		return tRepo.save(Ticket);
	}
	
	public Ticket findTicket(int tId)
	{
		Optional<Ticket> opTicket=tRepo.findById(tId);
		if(opTicket.isPresent())
		{
			return opTicket.get();
		}
		return null;
	}
	
	public Ticket deleteTicket(int tId)
	{
		Ticket t =findTicket(tId);
		tRepo.delete(t);
		
		return t;
	}
	
	public Ticket updateTicket(Ticket Ticket,int tId)
	{
		Ticket ext=findTicket(tId);
		
		if(ext!=null)
		{
			Ticket.setTId(tId);
			return tRepo.save(Ticket);
		}
		return null;
	}
	
	public List<Ticket> findAllTicket()
	{
		List<Ticket> Ticket=tRepo.findAll();
		return Ticket;
	}
	

}
