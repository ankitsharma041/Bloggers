package com.ankit.blog.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ankit.blog.payload.PostDTO;

@Service
public interface PostService {

	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

	PostDTO updatePost(PostDTO postDTO, Integer id);

	PostDTO getPostsById(Integer id);

	void deletePost(Integer id);

	PostDTO getAllPost();

	List<PostDTO> getPostsByCategoryId(Integer cInteger);

	List<PostDTO> getPostsByUserId(Integer uInteger);

	List<PostDTO> searchPost(String keyword);
		
}
