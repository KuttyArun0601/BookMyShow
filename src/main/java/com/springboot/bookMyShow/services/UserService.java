package com.springboot.bookMyShow.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springboot.bookMyShow.Entity.Booking;
import com.springboot.bookMyShow.Entity.User;
import com.springboot.bookMyShow.dao.BookingDao;
import com.springboot.bookMyShow.dao.UserDao;

import com.springboot.bookMyShow.dto.UserDto;
import com.springboot.bookMyShow.exceptions.BookingNotFound;
import com.springboot.bookMyShow.exceptions.LoginFailed;
import com.springboot.bookMyShow.exceptions.UserNotFound;
import com.springboot.bookMyShow.util.ResponceStructure;

@Service
public class UserService {
	
	@Autowired
	UserDao uDao;
	
	@Autowired
	BookingDao bDao;
	
	
	
	public ResponseEntity<ResponceStructure<UserDto>> saveUser(User user)
	{
		
			ResponceStructure<UserDto> str = new ResponceStructure<UserDto>();
			UserDto uDto = new UserDto();
			ModelMapper mapper=new ModelMapper();
			mapper.map(uDao.saveUser(user), uDto);
			
			str.setMessage(user.getUName()+" User has Saved");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(uDto);
			
			return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.CREATED);
			
		
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> findUser(int uId)
	{
		ResponceStructure<UserDto> str= new ResponceStructure<UserDto>();
		User u=uDao.findUser(uId);
		UserDto uDto = new UserDto();
		if(u!=null)
		{
			ModelMapper mapper=new ModelMapper();
			mapper.map(u, uDto);
			
			str.setMessage(u.getUName()+" user has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(uDto);
			
			return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.FOUND);
			
		}
		throw new UserNotFound("User not found with the given id"+uId);
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> deleteUser(int uId, String uEmail, String uPassword)
	{
		User u =uDao.userLogin(uEmail, uPassword);
		if(u!=null)
		{
			ResponceStructure<UserDto> str= new ResponceStructure<UserDto>();
			
			User exu=uDao.findUser(uId);
			UserDto uDto = new UserDto();
			if(exu!=null)
			{
				ModelMapper mapper=new ModelMapper();
				mapper.map(uDao.deleteUser(uId), uDto);
				
				str.setMessage(u.getUName()+" user has deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(uDto);
				
				return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
			}
			throw new UserNotFound("User not found with the given id"+uId);
		}
		throw new LoginFailed("Enter valid email & password");
		
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> updateUser(User user, int uId, String uEmail, String uPassword)
	{
		User u =uDao.userLogin(uEmail, uPassword);
		if(u!=null)
		{
			ResponceStructure<UserDto> str=new ResponceStructure<UserDto>();
			
			User exu=uDao.findUser(uId);
			UserDto uDto = new UserDto();
			if(exu!=null)
			{
				ModelMapper mapper=new ModelMapper();
				mapper.map(uDao.updateUser(user, uId), uDto);
				
				str.setMessage(user.getUName()+" user has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(uDto);
				
				return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
			}
			throw new UserNotFound("User not found with the given id"+uId);
		}
		throw new LoginFailed("Enter valid email & password");
		
	}
	
	
	public ResponseEntity<ResponceStructure<List<UserDto>>> findAllUser()
	{
		List<User> uList=uDao.findAllUser();
		List<UserDto> udList= new ArrayList<UserDto>();
		if(!uList.isEmpty())
		{
			
			for (User u : uList) {
				UserDto uDto = new UserDto();
				ModelMapper mapper=new ModelMapper();
				mapper.map(u, uDto);
				udList.add(uDto);
			}
			ResponceStructure<List<UserDto>> str= new ResponceStructure<List<UserDto>>();
			
			str.setMessage("All user's are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(udList);
			
			return new ResponseEntity<ResponceStructure<List<UserDto>>>(str,HttpStatus.FOUND);
			
		}
		throw new UserNotFound("User not found");
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> assignBookingToUser(int uId,int bId,String uEmail,String uPassword)
	{
		User vu=uDao.userLogin(uEmail, uPassword);
		if(vu!=null)
		{
			ResponceStructure<UserDto> str = new ResponceStructure<UserDto>();
			User u=uDao.findUser(uId);
			UserDto uDto= new UserDto();
			List<Booking> bList=u.getUBooking();
			Booking b=bDao.findBooking(bId);
			if(u!=null && b!=null)
			{
				bList.add(b);
				ModelMapper mapper=new ModelMapper();
				mapper.map(uDao.updateUser(u, uId), uDto);
				str.setMessage("booking assigned to user");
				str.setStatus(HttpStatus.OK.value());
				str.setData(uDto);
				
				return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
				
			}
			throw new BookingNotFound("booking not founded with the given id "+bId);
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> deleteBookingFromUser(int uId, int bId,String uEmail,String uPassword)
	{
		User vu=uDao.userLogin(uEmail, uPassword);
		if(vu!=null)
		{
			User u=uDao.findUser(uId);
			UserDto uDto= new UserDto();
			Booking b=bDao.findBooking(bId);
			List<Booking> bList=u.getUBooking();
			ResponceStructure<UserDto> str=new ResponceStructure<UserDto>();
			for (Booking booking : bList) {
				
				if(booking.getBId()==bId)
				{
					bList.remove(b);
					u.setUBooking(bList);
					bDao.deleteBooking(bId);
					ModelMapper mapper=new ModelMapper();
					mapper.map(uDao.updateUser(u, uId), uDto);
					str.setMessage("booking has deleted from user");
					str.setStatus(HttpStatus.OK.value());
					str.setData(uDto);
					
					return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
				}
				throw new BookingNotFound("booking not fount with the given id");
			}
			throw new BookingNotFound("booking not fount with the given id");
		}
		throw new LoginFailed("enter valid email & password");
	}
	
	public ResponseEntity<ResponceStructure<UserDto>> removeBookingFromUser(int uId, int bId,String uEmail,String uPassword)
	{
		User vu=uDao.userLogin(uEmail, uPassword);
		if(vu!=null)
		{
			User u=uDao.findUser(uId);
			UserDto uDto= new UserDto();
			Booking b=bDao.findBooking(bId);
			List<Booking> bList=u.getUBooking();
			ResponceStructure<UserDto> str=new ResponceStructure<UserDto>();
			for (Booking booking : bList) {
				
				if(booking.getBId()==bId)
				{
					bList.remove(b);
					u.setUBooking(bList);
					ModelMapper mapper=new ModelMapper();
					mapper.map(uDao.updateUser(u, uId), uDto);
					str.setMessage("booking has removed from user");
					str.setStatus(HttpStatus.OK.value());
					str.setData(uDto);
					
					return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
				}
				throw new BookingNotFound("booking not fount with the given id");
			}
			throw new BookingNotFound("booking not fount with the given id");
		}
		throw new LoginFailed("enter valid email & password");
	}
	
//	
//	public ResponseEntity<ResponceStructure<UserDto>> verifyUser(String uEmail,String uPassword)
//	{
//		ResponceStructure<UserDto> str= new ResponceStructure<UserDto>();
//		
//		List<User> uList= uDao.findAllUser();
//		
//		if(!uList.isEmpty())
//		{
//			for (User user : uList) {
//				if(user.getUEmail().equals(uEmail) && user.getUPassword().equals(uPassword))
//				{
//					str.setMessage("User logined successfullly");
//					str.setStatus(HttpStatus.OK.value());
//					str.setData(uDto);
//					return new ResponseEntity<ResponceStructure<UserDto>>(str,HttpStatus.OK);
//							
//				}
//				return null;
//			}
//		}
//		return null;
//		
//	}

}
