package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookMyShow.Entity.Shows;

public interface ShowsRepo extends JpaRepository<Shows, Integer>{

}
