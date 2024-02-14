package com.springboot.bookMyShow.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.User;

import com.springboot.bookMyShow.dao.UserDao;

import com.springboot.bookMyShow.dto.UserDto;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class UserService {
	
	@Autowired
	UserDao uDao;
	
	UserDto uDto = new UserDto();
	ModelMapper mapper=new ModelMapper();
	
	public ResponseEntity<ResponceStructure<UserDto>> saveUser(User user)
	{
		ResponceStructure<UserDto> str = new ResponceStructure<UserDto>();
		
		mapper.map(uDao.saveUser(user), uDto);
		
		str.setMessage(user.getUName()+"User has Saved");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(uDto);
		
		return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> findUser(int uId)
	{
		ResponceStructure<UserDto> str= new ResponceStructure<UserDto>();
		User u=uDao.findUser(uId);
		if(u!=null)
		{
			mapper.map(u, uDto);
			
			str.setMessage(u.getUName()+" user has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(uDto);
			
			return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.FOUND);
			
		}
		return null;
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> deleteUser(int uId)
	{
		ResponceStructure<UserDto> str= new ResponceStructure<UserDto>();
		
		User u=uDao.findUser(uId);
		if(u!=null)
		{
			mapper.map(uDao.deleteUser(uId), uDto);
			
			str.setMessage(u.getUName()+" user has deleted");
			str.setStatus(HttpStatus.OK.value());
			str.setData(uDto);
			
			return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
		}
		return null;
		
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> updateUser(User user, int uId)
	{
		
		ResponceStructure<UserDto> str=new ResponceStructure<UserDto>();
		
		User exu=uDao.findUser(uId);
		if(exu!=null)
		{
			mapper.map(uDao.updateUser(user, uId), uDto);
			
			str.setMessage(user.getUName()+" user has updated");
			str.setStatus(HttpStatus.OK.value());
			str.setData(uDto);
			
			return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
		}
		return null;
	}
	
	
	public ResponseEntity<ResponceStructure<List<UserDto>>> findAllUser()
	{
		List<User> uList=uDao.findAllUser();
		List<UserDto> udList= new ArrayList<UserDto>();
		if(!uList.isEmpty())
		{
			for (User u : uList) {
				
				mapper.map(u, uDto);
				udList.add(uDto);
			}
			ResponceStructure<List<UserDto>> str= new ResponceStructure<List<UserDto>>();
			
			str.setMessage("All user's are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(udList);
			
			return new ResponseEntity<ResponceStructure<List<UserDto>>>(str,HttpStatus.FOUND);
			
		}
		return null;
	}
	
	
	public ResponseEntity<ResponceStructure<UserDto>> verifyUser(String uEmail,String uPassword)
	{
		ResponceStructure<UserDto> str= new ResponceStructure<UserDto>();
		
		List<User> uList= uDao.findAllUser();
		
		if(!uList.isEmpty())
		{
			for (User user : uList) {
				if(user.getUEmail().equals(uEmail) && user.getUPassword().equals(uPassword))
				{
					str.setMessage("User logined successfullly");
					str.setStatus(HttpStatus.OK.value());
					str.setData(uDto);
					return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
							
				}
				return null;
			}
		}
		return null;
		
	}

}
