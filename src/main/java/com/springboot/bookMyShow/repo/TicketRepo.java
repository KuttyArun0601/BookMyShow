package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookMyShow.Entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
