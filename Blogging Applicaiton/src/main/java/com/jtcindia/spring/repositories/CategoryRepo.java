package com.jtcindia.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtcindia.spring.entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
