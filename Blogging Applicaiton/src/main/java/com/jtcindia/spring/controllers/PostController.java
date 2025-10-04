package com.jtcindia.spring.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jtcindia.spring.config.AppConstants;
import com.jtcindia.spring.entites.Post;
import com.jtcindia.spring.payloads.ApiResponse;
import com.jtcindia.spring.payloads.PostDto;
import com.jtcindia.spring.payloads.PostResponse;
import com.jtcindia.spring.payloads.imageResponse;
import com.jtcindia.spring.services.FileService;
import com.jtcindia.spring.services.PostService;

@RestController

public class PostController {

	@Autowired 
	private PostService postService;
	
//	@Autowired
//	private FileService fileService;
//	
//	@Value("${project.image}")
//	public String path;
//	
	//create 
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	
	//update
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto>updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto) {
	PostDto updatePost = this.postService.updatePost(postDto,postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	
	
	//delete
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is succesfully deleted !!",true);  	
	}
	
	
	  
	
	//get
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
	    PostDto postById = this.postService.getPostById(postId);
	    return new ResponseEntity<>(postById, HttpStatus.OK);
	} 

	//getAll
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>getAllPost(
			@RequestParam(value = "pageNumber" ,defaultValue=AppConstants.PAGE_NUMBER ,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize" ,defaultValue=AppConstants.PAGE_SIZE ,required = false)Integer pageSize,
			@RequestParam(value = "sortBy" ,defaultValue=AppConstants.SORT_BY ,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue=AppConstants.SORT_DIR ,required = false)String sortDir){
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	//get by user
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> Posts = this.postService.getpostsbyUser(userId);
		return new ResponseEntity<List<PostDto>>(Posts,HttpStatus.OK);
		
	}
	
	                            	
	
	//get by category
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
//	//post image upload
//	@PostMapping("post/image/upload/{postId}")
//	public ResponseEntity<imageResponse> uploadPostImage(@RequestParam("image") MultipartFile image) throws IOException{
//		String filaName = this.fileService.uploadImage(path, image);
//		return null;
//	}
	
	
}