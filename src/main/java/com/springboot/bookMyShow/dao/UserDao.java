package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.User;
import com.springboot.bookMyShow.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	UserRepo uRepo;
	
	public User saveUser(User user)
	{
		return uRepo.save(user);
	}
	
	
	public User findUser(int uId)
	{
		Optional<User> opUser= uRepo.findById(uId);
		if(opUser.isPresent())
		{
			return opUser.get();
		}
		return null;
	}
	
	public User deleteUser(int uId)
	{
		User u = findUser(uId);
		uRepo.delete(u);
		return u;
		
	}
	
	public User updateUser(User user, int uId)
	{
		User exu= findUser(uId);
		
		if(exu!= null)
		{
			user.setUId(uId);
			return uRepo.save(user);
		}
		return null;
	}
	
	public List<User> findAllUser()
	{
		
		return uRepo.findAll();
	}
	
	public User userLogin(String uEmail, String uPassword)
	{
		
		return uRepo.userLogin(uEmail, uPassword);
	}
}
