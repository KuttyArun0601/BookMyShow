package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.Booking;
import com.springboot.bookMyShow.repo.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	BookingRepo bRepo;
	
	public Booking saveBooking(Booking book)
	{
		return bRepo.save(book);
	}
	
	public Booking findBokking(int bId)
	{
		Optional<Booking> opBooking=bRepo.findById(bId);
		if(opBooking.isPresent())
		{
			return opBooking.get();
		}
		return null;
	}
	
	public Booking deleteBooking(int bId)
	{
		Booking b=findBokking(bId);
		bRepo.delete(b);
		return b;
	}
	
	public Booking updateBooking(Booking book, int bId)
	{
		Booking exb=findBokking(bId);
		if(exb!=null)
		{
			book.setBId(bId);
			return bRepo.save(book);
		}
		return null;
	}
	
	public List<Booking> findAllBooking()
	{
		List<Booking> book=bRepo.findAll();
		return book;
	}
}
