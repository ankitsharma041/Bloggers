package com.ankit.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

	private int id;
	private String title;
	//private String image;
	private String content;
	private Date date;
	private UserDTO user;
	private CategoryDTO category;
	private Set<CommentDTO> comments = new HashSet<>();
	private String message;
	private int statusCode;
}
