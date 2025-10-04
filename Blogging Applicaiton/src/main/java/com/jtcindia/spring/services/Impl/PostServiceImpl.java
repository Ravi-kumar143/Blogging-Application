package com.jtcindia.spring.services.Impl;

import java.util.Date;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jtcindia.spring.entites.Category;
import com.jtcindia.spring.entites.Post;
import com.jtcindia.spring.entites.User;
import com.jtcindia.spring.exceptions.ResourceNotFoundException;
import com.jtcindia.spring.payloads.PostDto;
import com.jtcindia.spring.payloads.PostResponse;
import com.jtcindia.spring.repositories.CategoryRepo;
import com.jtcindia.spring.repositories.PostRepo;
import com.jtcindia.spring.repositories.UserRepo;
import com.jtcindia.spring.services.AiTitleService;
import com.jtcindia.spring.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AiTitleService aiTitleService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
	User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));	
	
	Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
	
	Post post = this.modelMapper.map(postDto, Post.class);

	post.setImageName("default.png");
	post.setAddeddate(new Date());
	post.setCategory(category);
	post.setUser(user);

	Post newPost = this.postRepo.save(post);
	return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post save = this.postRepo.save(post);
		return this.modelMapper.map(save,PostDto.class );
	}

	@Override
	public void deletePost(Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId",postId));	
	this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","PostId",postId));
	return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir) {
	
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable pageable=PageRequest.of(pageNumber ,pageSize, sort);
		
	Page<Post>pagePost = this.postRepo.findAll(pageable);
	List<Post> Allpost = pagePost.getContent();
	
		
	List<PostDto> PostDto = Allpost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
	PostResponse postResponse=new PostResponse();
	
	postResponse.setContent(PostDto);
	postResponse.setPageNumber(pagePost.getNumber());
	postResponse.setPageSize(pagePost.getSize());
	postResponse.setTotalElements(pagePost.getTotalElements());
	
	postResponse.setTotalPages(pagePost.getTotalPages());
	postResponse.setLastPage(pagePost.isLast());
	
	return postResponse;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
	    Category cat = this.categoryRepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

	    List<Post> posts = this.postRepo.findByCategory(cat);

	    List<PostDto> postDtos = posts.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    return postDtos;
	}

	@Override
	public List<PostDto> getpostsbyUser(Integer userId) {
	    User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

	    List<Post> posts = this.postRepo.findByUser(user);

	    List<PostDto> postDtos = posts.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    return postDtos;
	}


	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream()
				.map((post)-> this.modelMapper.map(post,PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

}
