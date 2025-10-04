package com.jtcindia.spring.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtcindia.spring.entites.Category;
import com.jtcindia.spring.exceptions.ResourceNotFoundException;
import com.jtcindia.spring.payloads.CategoryDto;
import com.jtcindia.spring.repositories.CategoryRepo;
import com.jtcindia.spring.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedcat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
//		Category cat = this.modelMapper.map(categoryId, Category.class);
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedcat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId)); 
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	Category cat = this.categoryRepo.findById(categoryId)
			.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId",categoryId));
	return this.modelMapper.map(cat, CategoryDto.class);
	}


	@Override
	public List<CategoryDto> getCategroies() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> CatDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
		.collect(Collectors.toList());
		return CatDtos;
	}
}
