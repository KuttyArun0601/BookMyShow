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
import com.springboot.bookMyShow.Entity.User;
import com.springboot.bookMyShow.dao.AdminDao;
import com.springboot.bookMyShow.dao.TheatreDao;
import com.springboot.bookMyShow.dao.UserDao;
import com.springboot.bookMyShow.dto.AdminDto;
import com.springboot.bookMyShow.exceptions.AdminNotFound;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.exceptions.TheatreNotFound;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class AdminService {

	@Autowired
	AdminDao aDao;
	
	@Autowired
	TheatreDao tDao;
	
	@Autowired
	UserDao uDao;
	
	ModelMapper mapper =new  ModelMapper();
	
	public ResponseEntity<ResponceStructure<AdminDto>> saveAdmin(Admin admin)
	{
		ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
		AdminDto aDto= new AdminDto();
		mapper.map(aDao.saveAdmin(admin), aDto);
		
		str.setMessage(admin.getAName()+"Admin has Saved");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(aDto);
		
		return new ResponseEntity<ResponceStructure<AdminDto>>(str,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponceStructure<AdminDto>> findAdmin(int aId)
	{
		ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
		AdminDto aDto= new AdminDto();
		Admin a = aDao.findAdmin(aId);
		mapper.map(a, aDto);
		if(a!=null)
		{
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
			AdminDto aDto= new AdminDto();
			Admin a=aDao.findAdmin(aId);
			if(a!=null)
			{
				Admin delA=aDao.deleteAdmin(aId);
				mapper.map(delA, aDto);
				
				str.setMessage("Admin ha Deleted");
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
			AdminDto aDto= new AdminDto();
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
		ResponceStructure<List<AdminDto>> str = new ResponceStructure<List<AdminDto>>();
		List<Admin>  adList = aDao.findAllAdmin();
		List<AdminDto> aDtoList =new ArrayList<AdminDto>();
		if(! adList.isEmpty())
		{
			for(Admin a : adList  )
			{
				AdminDto aDto= new AdminDto();
				mapper.map(a,aDto);
				aDtoList.add(aDto);
				
			}
			
			str.setMessage("All Admin's are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(aDtoList);
			return new ResponseEntity<ResponceStructure<List<AdminDto>>>(str,HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found");
	}
	
	public ResponseEntity<ResponceStructure<AdminDto>> assignTheatreToAdmin(int aId,int tId,String aEmail, String aPassword)
	{
		Admin va=aDao.adminLogin(aEmail, aPassword);
		if(va!=null)
		{
			Admin a=aDao.findAdmin(aId);
			AdminDto aDto = new AdminDto();
			Theatre t= tDao.findTheatre(tId);
			List<Theatre> tList=a.getATheatre();
			ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
			if (a!=null && t != null) {
				tList.add(t);
				mapper.map(aDao.updateAdmin(a, aId), aDto);
				str.setMessage("Theatre assigned to the admin");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				return new ResponseEntity<ResponceStructure<AdminDto>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id");
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<Admin>> deleteTheatreFromAdmin(int aId,int tId,String aEmail, String aPassword)
	{
		Admin va=aDao.adminLogin(aEmail, aPassword);
		if(va!=null)
		{
			Theatre t= tDao.findTheatre(tId);
			Admin a=aDao.findAdmin(aId);
			List<Theatre> tList=a.getATheatre();
			ResponceStructure<Admin> str= new ResponceStructure<Admin>();
			for (Theatre theatre : tList) {
				int id=theatre.getTId();
				if(id==tId)
				{
					tList.remove(t);
//					tList.remove(tId);
					a.setATheatre(tList);
					tDao.deleteTheatre(id);
					str.setMessage(theatre.getTName()+" is deleted from "+a.getAName());
					str.setStatus(HttpStatus.OK.value());
					str.setData(aDao.updateAdmin(a, a.getAId()));
					
					return new ResponseEntity<ResponceStructure<Admin>>(str,HttpStatus.OK);
				}
				throw new TheatreNotFound("theatre not found with the given id");
			}
			return null;
		}
		throw new AdminNotFound("Enter valid email & password");
		
	}
	
	public ResponseEntity<ResponceStructure<AdminDto>> assignUserToAdmin(int aId,int uId,String aEmail, String aPassword)
	{
		Admin va=aDao.adminLogin(aEmail, aPassword);
		if(va!=null)
		{
			Admin a=aDao.findAdmin(aId);
			AdminDto aDto = new AdminDto();
			User u= uDao.findUser(uId);
			List<User> uList=a.getAUser();
			ResponceStructure<AdminDto> str= new ResponceStructure<AdminDto>();
			if (a!=null && u != null) {
				uList.add(u);
				mapper.map(aDao.updateAdmin(a, aId), aDto);
				str.setMessage("Theatre assigned to the admin");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				return new ResponseEntity<ResponceStructure<AdminDto>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id");
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<Admin>> deleteUserFromAdmin(int aId,int uId,String aEmail, String aPassword)
	{
		Admin va=aDao.adminLogin(aEmail, aPassword);
		if(va!=null)
		{
			User u= uDao.findUser(uId);
			Admin a=aDao.findAdmin(aId);
			List<User> uList=a.getAUser();
			ResponceStructure<Admin> str= new ResponceStructure<Admin>();
			for (User user : uList) {
				int id=user.getUId();
				if(id==uId)
				{
					uList.remove(u);
//					uList.remove(tId);
					a.setAUser(uList);
					tDao.deleteTheatre(id);
					str.setMessage(user.getUName()+" is deleted from "+a.getAName());
					str.setStatus(HttpStatus.OK.value());
					str.setData(aDao.updateAdmin(a, a.getAId()));
					
					return new ResponseEntity<ResponceStructure<Admin>>(str,HttpStatus.OK);
				}
				throw new TheatreNotFound("");
			}
			return null;
		}
		throw new AdminNotFound("Enter valid email & password");
		
	}
	
	public ResponseEntity<ResponceStructure<Admin>> removeUserFromAdmin(int aId,int uId,String aEmail, String aPassword)
	{
		Admin va=aDao.adminLogin(aEmail, aPassword);
		if(va!=null)
		{
			User u= uDao.findUser(uId);
			Admin a=aDao.findAdmin(aId);
			List<User> uList=a.getAUser();
			ResponceStructure<Admin> str= new ResponceStructure<Admin>();
			for (User user : uList) {
				int id=user.getUId();
				if(id==uId)
				{
					uList.remove(u);
//					uList.remove(tId);
					a.setAUser(uList);
					str.setMessage(user.getUName()+" is removed from "+a.getAName());
					str.setStatus(HttpStatus.OK.value());
					str.setData(aDao.updateAdmin(a, a.getAId()));
					
					return new ResponseEntity<ResponceStructure<Admin>>(str,HttpStatus.OK);
				}
				throw new TheatreNotFound("theatre not found with given id");
			}
			return null;
		}
		throw new AdminNotFound("Enter valid email & password");
		
	}
	
	
//	public ResponseEntity<ResponceStructure<Admin>> removeTheatreFromAdmin(int aId,int tId,String aEmail, String aPassword)
//	{
//		Admin va=aDao.adminLogin(aEmail, aPassword);
//		if(va!=null)
//		{
//			Theatre t= tDao.findTheatre(tId);
//			Admin a=aDao.findAdmin(aId);
//			List<Theatre> tList=a.getATheatre();
//			ResponceStructure<Admin> str= new ResponceStructure<Admin>();
//			for (Theatre theatre : tList) {
//				int id=theatre.getTId();
//				if(id==tId)
//				{
//					
//					tList.remove(t);
//					a.setATheatre(tList);
//					tDao.deleteTheatre(id);
//					str.setMessage(theatre.getTName()+" is deleted from "+a.getAName());
//					str.setStatus(HttpStatus.OK.value());
//					str.setData(aDao.updateAdmin(a, a.getAId()));
//					
//					return new ResponseEntity<ResponceStructure<Admin>>(str,HttpStatus.OK);
//				}
//				throw new TheatreNotFound("");
//			}
//			return null;
//		}
//		throw new AdminNotFound("Enter valid email & password");
//		
//	}
	
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
