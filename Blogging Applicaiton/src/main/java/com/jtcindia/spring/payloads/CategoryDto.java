package com.jtcindia.spring.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min=4)
	private String categoryTitle;
	
	@NotBlank 
	@Size(max=100)
	private String categoryDescription;
	
}
