package com.jtcindia.spring.services.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtcindia.spring.payloads.UserDto;
import com.jtcindia.spring.repositories.UserRepo;
import com.jtcindia.spring.services.UserService;
import com.jtcindia.spring.entites.*;
import com.jtcindia.spring.exceptions.*;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser=this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(() ->new ResourceNotFoundException("user", "Id",userId)); 
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers()  {
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));

		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto,User.class);
				
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
		
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
				
				
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

//	@Override
//	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
	//	return null;
	//}
}
