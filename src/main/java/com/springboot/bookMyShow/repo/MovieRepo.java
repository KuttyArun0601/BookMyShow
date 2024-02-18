package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookMyShow.Entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer>{

	public Movie findBymTitle(String mTitle);
}
