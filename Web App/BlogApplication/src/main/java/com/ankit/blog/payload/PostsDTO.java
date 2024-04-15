package com.ankit.blog.payload;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostsDTO {

	private String title;
	private String content;
	private String image;
	private Date date;
	private CategoryDTO category;
	private UserDTO user;
}
