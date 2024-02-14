package com.springboot.bookMyShow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.dto.AdminDto;
import com.springboot.bookMyShow.services.AdminService;
import com.springboot.bookMyShow.util.ResponceStructure;

@RestController
@RequestMapping("Admin")
public class AdminController {
	
	@Autowired
	AdminService aService;
	
	@PostMapping("save")
	public  ResponseEntity<ResponceStructure<AdminDto>> saveAdmin(Admin admin)
	{
		return aService.saveAdmin(admin);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<AdminDto>> findAdmin(int aId)
	{
		return aService.findAdmin(aId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<AdminDto>> deleteAdmin(int aId)
	{
		return aService.deleteAdmin(aId);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<AdminDto>> updateAdmin(Admin admin,int aId)
	{
		return aService.updateAdmin(admin, aId);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<AdminDto>>> findAllAdmins()
	{
		return aService.findAllAdmin();
	}
	
	@GetMapping("verify")
	public ResponseEntity<ResponceStructure<AdminDto>> verifyAdmin(String aEmail,String aPassword)
	{
		return aService.verifyAdmin(aEmail, aPassword);
	}
	

}
