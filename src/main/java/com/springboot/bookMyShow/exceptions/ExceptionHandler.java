package com.springboot.bookMyShow.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> adminNotFoundException(AdminNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("Admin not found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> loginFailedException(LoginFailed ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("login failed");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> movieNotFoundException(MovieNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("Movie Not Found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> bookingNotFoundException(BookingNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("Booking not found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> SeatsNotFoundException(SeatsNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("Seats not found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> ShowsNotFoundException(ShowsNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("Shows not found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> theatreNotFoundException(TheatreNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("Theatre not found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> thicketNotFoundException(TicketNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("Ticket not found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> UserNotFoundException(UserNotFound ex)
	{
		ResponceStructure<String> str=new ResponceStructure<String>();
		
		str.setMessage("User not found");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setData(ex.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Object> handleConstraintViolationExcepption(ConstraintViolationException ex)
	{
		ResponceStructure<Object> str= new ResponceStructure<Object>();
		Map<String, String> hasmap= new HashMap<>();
		
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) 
		{
			String field = violation.getPropertyPath().toString();
			String message=violation.getMessage();
			hasmap.put(field, message);
			
		}
		str.setMessage("Add proper details");
		str.setStatus(HttpStatus.FORBIDDEN.value());
		str.setData(hasmap);
		
		return new ResponseEntity<Object>(str, HttpStatus.BAD_REQUEST);
	}
}
