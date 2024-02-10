package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.Theatre;
import com.springboot.bookMyShow.repo.TheatreRepo;

@Repository
public class TheatreDao {

	@Autowired
	TheatreRepo tRepo;
	
	public Theatre saveTheatre(Theatre theatre)
	{
		return tRepo.save(theatre);
	}
	
	public Theatre findTheatre(int tId)
	{
		Optional<Theatre> optheatre=tRepo.findById(tId);
		
		if(optheatre.isPresent())
		{
			return optheatre.get();
		}
		return null;
	}
	
	public Theatre deleteTheatre(int tId)
	{
		Theatre t= findTheatre(tId);
		tRepo.delete(t);
		return t;
	}
	
	public Theatre updatetheatre(Theatre theatre,int tid)
	{
		Theatre ext=findTheatre(tid);
		if(ext!=null)
		{
			theatre.setTId(tid);
			return tRepo.save(theatre);
		}
		return null;
	}
	
	public List<Theatre> findAllTheatre()
	{
		List<Theatre> theatre=tRepo.findAll();
		return theatre;
	}
}
