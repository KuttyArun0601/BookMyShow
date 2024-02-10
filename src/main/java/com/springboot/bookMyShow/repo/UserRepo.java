package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookMyShow.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
