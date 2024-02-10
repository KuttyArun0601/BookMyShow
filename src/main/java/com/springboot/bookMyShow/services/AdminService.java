package com.springboot.bookMyShow.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.dao.AdminDao;
import com.springboot.bookMyShow.dto.AdminDto;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class AdminService {

	@Autowired
	AdminDao aDao;
	
	AdminDto aDto= new AdminDto();
	ModelMapper mapper =new  ModelMapper();
	
	public ResponseEntity<ResponceStructure<AdminDto>> saveAdmin(Admin admin)
	{
		ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
		
		mapper.map(aDao.saveAdmin(admin), aDto);
		
		str.setMessage(admin.getAName()+"Admin has Saved");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(aDto);
		
		return new ResponseEntity<ResponceStructure<AdminDto>>(str,HttpStatus.CREATED);
	}
	
	
	
}
