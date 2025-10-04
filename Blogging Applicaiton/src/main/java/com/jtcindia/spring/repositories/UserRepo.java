package com.jtcindia.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtcindia.spring.entites.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	
}
