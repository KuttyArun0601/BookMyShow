package com.springboot.bookMyShow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.dto.AdminDto;
import com.springboot.bookMyShow.services.AdminService;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	AdminService aService;
	
	@PostMapping("save")
	public  ResponseEntity<ResponceStructure<AdminDto>> saveAdmin(@Valid @RequestBody Admin admin, BindingResult result)
	{
		return aService.saveAdmin(admin);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<AdminDto>> findAdmin(@RequestParam int aId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.findAdmin(aId, aEmail, aPassword);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<AdminDto>> deleteAdmin(@RequestParam int aId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.deleteAdmin(aId, aEmail, aPassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<AdminDto>> updateAdmin(@RequestBody Admin admin,@RequestParam int aId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.updateAdmin(admin, aId, aEmail, aPassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<AdminDto>>> findAllAdmins()
	{
		return aService.findAllAdmin();
	}
	
	
	@PutMapping("assignTheatre")
	public ResponseEntity<ResponceStructure<AdminDto>> assignTheatre(@RequestParam int aId,@RequestParam int tId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.assignTheatreToAdmin(aId, tId, aEmail, aPassword);
	}
	@PutMapping("deleteTheatre")
	public ResponseEntity<ResponceStructure<Admin>> deleteTheatre(@RequestParam int aId,@RequestParam int tId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.deleteTheatreFromAdmin(aId, tId, aEmail, aPassword);
	}
	@PutMapping("removeTheatre")
	public ResponseEntity<ResponceStructure<Admin>> removeTheatre(@RequestParam int aId,@RequestParam int tId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.removeTheatreFromAdmin(aId, tId, aEmail, aPassword);
	}
	@PutMapping("assignUser")
	public ResponseEntity<ResponceStructure<AdminDto>> assignUser(@RequestParam int aId,@RequestParam int uId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.assignTheatreToAdmin(aId, uId, aEmail, aPassword);
	}
	@PutMapping("deleteUser")
	public ResponseEntity<ResponceStructure<Admin>> deleteUser(@RequestParam int aId,@RequestParam int uId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.deleteUserFromAdmin(aId, uId, aEmail, aPassword);
	}
	@PutMapping("removeUser")
	public ResponseEntity<ResponceStructure<Admin>> removeUser(@RequestParam int aId,@RequestParam int uId,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return aService.removeUserFromAdmin(aId, uId, aEmail, aPassword);
	}
//	@GetMapping("verify")
//	public ResponseEntity<ResponceStructure<AdminDto>> verifyAdmin(String aEmail,String aPassword)
//	{
//		return aService.verifyAdmin(aEmail, aPassword);
//	}
//	

}
