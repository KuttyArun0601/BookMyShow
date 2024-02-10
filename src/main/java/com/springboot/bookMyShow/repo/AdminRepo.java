package com.springboot.bookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookMyShow.Entity.Admin;

public interface AdminRepo  extends JpaRepository<Admin, Integer>{

}
