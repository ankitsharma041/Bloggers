package com.ankit.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

	private int categoryId;
	private String title;
	private String message;
	private int statusCode;
}
