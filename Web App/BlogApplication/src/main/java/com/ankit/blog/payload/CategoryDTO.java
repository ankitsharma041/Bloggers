package com.ankit.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

	private int id;
//	private String title;
//	private String description;
	private String message;
	private int statusCode;
}
