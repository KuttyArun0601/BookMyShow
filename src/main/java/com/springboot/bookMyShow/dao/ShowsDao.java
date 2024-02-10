package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.Shows;
import com.springboot.bookMyShow.repo.ShowsRepo;

@Repository
public class ShowsDao {

	@Autowired
	ShowsRepo sRepo;
	
	public Shows saveShows(Shows shows)
	{
		return sRepo.save(shows);
	}
	
	public Shows findShows(int sId)
	{
		Optional<Shows> opShow=sRepo.findById(sId);
		if(opShow.isPresent())
		{
			return opShow.get();
		}
		return null;
	}
	
	public Shows deleteShows(int sId)
	{
		Shows s=findShows(sId);
		sRepo.delete(s);
		
		return s;
	}
	
	public Shows updateShows(Shows shows, int sId)
	{
		Shows exs=findShows(sId);
		if(exs!=null)
		{
			shows.setSId(sId);
			return sRepo.save(shows);
		}
		return null;
	}
	
	public List<Shows> findAllShows()
	{
		List<Shows> shows=sRepo.findAll();
		return shows;
	}
	
	
}
