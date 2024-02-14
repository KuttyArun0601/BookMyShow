package com.springboot.bookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.repo.AdminRepo;

@Repository
public class AdminDao {

	@Autowired
	AdminRepo aRepo;
	
	public Admin saveAdmin(Admin admin)
	{
		return aRepo.save(admin);
	}
	
	public Admin findAdmin(int aId)
	{
		Optional<Admin> opAdmin = aRepo.findById(aId);
		if(opAdmin.isPresent())
		{
			return opAdmin.get();
		}
		return null;
	}
	
	public Admin deleteAdmin(int aId)
	{
		Admin a =findAdmin(aId);
		aRepo.delete(a);
		
		return a;
	}
	
	public Admin updateAdmin(Admin admin , int  aId)
	{
		Admin exa= findAdmin(aId);
		
		if(exa!=null)
		{
			admin.setAId(aId);
			return aRepo.save(admin);
		}
		return null;
	}
	
	public List<Admin> findAllAdmin()
	{
		List<Admin> admin = aRepo.findAll();
		return admin;
	}
	
	public Admin adminLogin(String aEmail,String aPassword) {
		
		return aRepo.adminLogin(aEmail, aPassword);
	}
}
