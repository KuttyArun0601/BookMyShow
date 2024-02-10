package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookMyShow.Entity.Seats;

public interface SeatsRepo extends JpaRepository<Seats, Integer> {

}
