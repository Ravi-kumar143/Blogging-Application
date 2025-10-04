package com.jtcindia.spring.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto> content;
	
	private int PageNumber;
	
	private int PageSize;
	
	private Long TotalElements;
	
	private int TotalPages;
	
	private Boolean LastPage;

}
