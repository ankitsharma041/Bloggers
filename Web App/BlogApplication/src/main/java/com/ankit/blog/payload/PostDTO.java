package com.ankit.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.ankit.blog.entities.Category;
import com.ankit.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

	private int id;
	private String title;
	private String image;
	private String content;
	private Date date;
<<<<<<< Updated upstream
	private User user;
	private Category category;
=======
	//private UserDTO user;
	//private CategoryDTO category;
>>>>>>> Stashed changes
	private Set<CommentDTO> comments = new HashSet<>();
	private String message;
	private int statusCode;
}
