package com.jtcindia.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtcindia.spring.entites.Category;
import com.jtcindia.spring.entites.Post;
import com.jtcindia.spring.entites.User;


public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title); 
	
//	@Query("select p from Post p where p.title like :key")
//	List<Post> searchByTitle(@Param("key") String title); 
}
