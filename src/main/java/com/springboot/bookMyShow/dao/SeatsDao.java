package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.Seats;
import com.springboot.bookMyShow.repo.SeatsRepo;

@Repository
public class SeatsDao {

	
	@Autowired
	SeatsRepo seatsRepo;
	
	public Seats saveSeats(Seats seats)
	{
		return seatsRepo.save(seats);
	}
	
	public Seats findSeats(int sId)
	{
		Optional<Seats> opSeats=seatsRepo.findById(sId);
		if(opSeats.isPresent())
		{
			return opSeats.get();
		}
		return null;
	}
	
	public Seats deleteSeats(int sId)
	{
		Seats s =findSeats(sId);
		seatsRepo.delete(s);
		
		return s;
	}
	
	public Seats updateSeats(Seats seats,int sId)
	{
		Seats exs=findSeats(sId);
		
		if(exs!=null)
		{
			seats.setSId(sId);
			return seatsRepo.save(seats);
		}
		return null;
	}
	
	public List<Seats> findAllSeats()
	{
		List<Seats> seats=seatsRepo.findAll();
		return seats;
	}
	
}
