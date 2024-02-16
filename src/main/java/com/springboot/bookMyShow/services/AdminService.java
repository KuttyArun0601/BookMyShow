package com.springboot.bookMyShow.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.bookMyShow.Entity.Admin;
import com.springboot.bookMyShow.Entity.Theatre;
import com.springboot.bookMyShow.dao.AdminDao;
import com.springboot.bookMyShow.dao.TheatreDao;
import com.springboot.bookMyShow.dto.AdminDto;
import com.springboot.bookMyShow.exceptions.AdminNotFound;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class AdminService {

	@Autowired
	AdminDao aDao;
	
	@Autowired
	TheatreDao tDao;
	
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
	
	public ResponseEntity<ResponceStructure<AdminDto>> findAdmin(int aId)
	{
		ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
		Admin a = aDao.findAdmin(aId);
		if(a!=null)
		{
			mapper.map(a, aDto);
			
			str.setMessage(a.getAName()+"Admin has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(aDto);
			
			return new  ResponseEntity<ResponceStructure<AdminDto>>(str,HttpStatus.FOUND);
			
		}
		throw new AdminNotFound("Admin not found with the given id "+aId);
	}
	
	public ResponseEntity<ResponceStructure<AdminDto>> deleteAdmin(int aId ,String aEmail,String aPassword)
	{
		Admin exa=aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<AdminDto> str=new ResponceStructure<AdminDto>();
			Admin a=aDao.findAdmin(aId);
			if(a!=null)
			{
				mapper.map(aDao.deleteAdmin(aId), aDto);
				
				str.setMessage(a.getAName()+"Admin ha Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				
				return new ResponseEntity<ResponceStructure<AdminDto>>(str,HttpStatus.OK);
				
			}
			throw new AdminNotFound("Admin not found with the given id "+aId);
		}
		throw new LoginFailed("Enter the valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<AdminDto>> updateAdmin(Admin admin,int aId,String aEmail,String aPassword)
	{
		Admin exa = aDao.adminLogin(aEmail, aPassword);
		if(exa!=null)
		{
			ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
			Admin a=aDao.findAdmin(aId);
			if(a!=null)
			{
				mapper.map(aDao.updateAdmin(admin, aId), aDto);
				
				str.setMessage(admin.getAName()+"Admin has Updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				
				return new ResponseEntity<ResponceStructure<AdminDto>>(str,HttpStatus.OK);
			}
			throw new AdminNotFound("Admin not found with the given id "+aId);
		}
		throw new LoginFailed("Enter the valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<List<AdminDto>>> findAllAdmin()
	{
		List<Admin>  adList = aDao.findAllAdmin();
		List<AdminDto> aDtoList =new ArrayList<AdminDto>();
		if(! adList.isEmpty())
		{
			for(Admin a : adList  )
			{
				mapper.map(a,aDto);
				aDtoList.add(aDto);
			}
			ResponceStructure<List<AdminDto>> str = new ResponceStructure<List<AdminDto>>();
			str.setMessage("All Admin's are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(aDtoList);
			return new ResponseEntity<ResponceStructure<List<AdminDto>>>(str,HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found");
	}
	
	public ResponseEntity<ResponceStructure<Admin>> deleteTheatreFromAdmin(int aId,int tId,String aEmail, String aPassword)
	{
		Admin a=aDao.findAdmin(aId);
		List<Theatre> tList=a.getATheatre();
		ResponceStructure<Admin> str= new ResponceStructure<Admin>();
		for (Theatre theatre : tList) {
			int id=theatre.getTId();
			if(id==tId)
			{
				tDao.deleteTheatre(id);
				str.setMessage(theatre.getTName()+" is deleted from "+a.getAName());
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDao.updateAdmin(a, a.getAId()));
				
				return new ResponseEntity<ResponceStructure<Admin>>(str,HttpStatus.OK);
			}
			return null;
		}
		return null;
		
	}
	
//	public ResponseEntity<ResponceStructure<AdminDto>> verifyAdmin(String aEmail,String aPassword)
//	{
//		ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
//		
//		List<Admin> aList= aDao.findAllAdmin();
//		
//		if(!aList.isEmpty())
//		{
//			for (Admin admin : aList) {
//				if(admin.getAEmail().equals(aEmail) && admin.getAPassword().equals(aPassword))
//				{
//					mapper.map(admin, aDto);
//					str.setMessage("Admin login successfullly");
//					str.setStatus(HttpStatus.OK.value());
//					str.setData(aDto);
//					return new ResponseEntity<ResponceStructure<AdminDto>>(str,HttpStatus.OK);
//							
//				}
//			}
//		}
//		return null;
//		
//	}
	
	
}
