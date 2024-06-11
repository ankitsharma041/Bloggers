package com.ankit.blog.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

	private String message;
	private int statusCode;
	private String name;
	private String email;
	private String password;
	private String about;
	private int id;
}
