package com.jtcindia.spring.services;

import java.util.List;

import com.jtcindia.spring.entites.Post;
import com.jtcindia.spring.payloads.PostDto;
import com.jtcindia.spring.payloads.PostResponse;

public interface PostService {

	//create
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	
	//update
	public PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	public void deletePost(Integer postId);
	
	//get
	public PostDto getPostById(Integer postId);
	
	//getAll
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	
	//get all post by category
	List<PostDto>getPostsByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto>getpostsbyUser(Integer userId);
	
	//search post 
	List<PostDto> searchPosts(String keywords);

}
