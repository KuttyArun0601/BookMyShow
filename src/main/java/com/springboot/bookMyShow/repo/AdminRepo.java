package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.bookMyShow.Entity.Admin;

public interface AdminRepo  extends JpaRepository<Admin, Integer>{
	
	@Query("select a from Admin a where a.aEmail=?1 and a.aPassword=?2")
	public Admin adminLogin(String aEmail, String aPassword);
	

}
