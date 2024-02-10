package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookMyShow.Entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer>{

}
