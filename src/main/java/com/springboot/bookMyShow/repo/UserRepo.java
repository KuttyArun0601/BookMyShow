package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.bookMyShow.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.uEmail=?1 and u.uPassword=?2")
	public User userLogin(String uEmail,String uPassword);
}
