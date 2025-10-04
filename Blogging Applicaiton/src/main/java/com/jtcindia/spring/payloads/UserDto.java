package com.jtcindia.spring.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=3 , message = "User must be min 4 charcaters !!")
	private String name;
	
	@Email
	@Size(message = "Email is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min = 4 , max = 15 , message = "Password must be min 4 and max 15 characaters!!")
	private String password;
	
	@NotEmpty
	private String about;

}
