package com.ankit.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	private String id;
	private String name;
	private String email;
	private String password;
	private String about;
	private String message;
	private int statusCode;

}
