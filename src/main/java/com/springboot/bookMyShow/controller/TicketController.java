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

import com.springboot.bookMyShow.Entity.Ticket;
import com.springboot.bookMyShow.services.TicketService;
import com.springboot.bookMyShow.util.ResponceStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("ticket")
public class TicketController {
	
	
		
		@Autowired
		TicketService tService;
		
		@PostMapping("save")
		public ResponseEntity<ResponceStructure<Ticket>> saveTicket(@Valid @RequestBody Ticket ticket,BindingResult result ,@RequestParam String uEmail,@RequestParam String uPassword)
		{
			return tService.saveTicket(ticket, uEmail, uPassword);
		}
		
		@GetMapping("find")
		public ResponseEntity<ResponceStructure<Ticket>> findTicket(@RequestParam int tId)
		{
			return tService.findTicket(tId);
		}
		
		@DeleteMapping("delete")
		public ResponseEntity<ResponceStructure<Ticket>> delelteTicket(@RequestParam int tId ,@RequestParam String uEmail,@RequestParam String uPassword)
		{
			return tService.deleteTicket(tId, uEmail, uPassword);
		}
		
		@PutMapping("update")
		public ResponseEntity<ResponceStructure<Ticket>> updateTicket(@RequestBody Ticket ticket,@RequestParam int tId ,@RequestParam String uEmail,@RequestParam String uPassword)
		{
			return tService.updateTicket(ticket, tId, uEmail, uPassword);
		}
		
		@GetMapping("findAll")
		public ResponseEntity<ResponceStructure<List<Ticket>>> findAllTicket()
		{
			return tService.findAllTicket();
		}

	

}
