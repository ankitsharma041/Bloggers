package com.ankit.blog.payload;


import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

	private String title;
	private String content;
	private String image;
	private Date date;
	private CategoryDTO category;
	private UserDTO user;

}
