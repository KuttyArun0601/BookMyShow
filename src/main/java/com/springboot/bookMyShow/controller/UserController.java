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

import com.springboot.bookMyShow.Entity.User;
import com.springboot.bookMyShow.dto.UserDto;
import com.springboot.bookMyShow.services.UserService;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService uService;
	
	@PostMapping("save")
	public ResponseEntity<ResponceStructure<UserDto>> saveUser(@Valid User user,BindingResult result)
	{
		return uService.saveUser(user);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponceStructure<UserDto>> findUser(int uId)
	{
		return uService.findUser(uId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponceStructure<UserDto>> delelteUser(@RequestParam int uId ,@RequestParam String uEmail,@RequestParam String uPassword)
	{
		return uService.deleteUser(uId, uEmail, uPassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponceStructure<UserDto>> updateUser(@RequestBody User user,@RequestParam int uId,@RequestParam String uEmail,@RequestParam String uPassword)
	{
		return uService.updateUser(user, uId, uEmail, uPassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponceStructure<List<UserDto>>> findAllUsers()
	{
		return uService.findAllUser();
	}
	
//	@GetMapping("verify")
//	public ResponseEntity<ResponceStructure<UserDto>> verifyUser(String uEmail, String uPassword)
//	{
//		return uService.verifyUser(uEmail, uPassword);
//	}
}

