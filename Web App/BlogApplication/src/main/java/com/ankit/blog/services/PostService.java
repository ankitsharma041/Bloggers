package com.ankit.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.Post;
import com.ankit.blog.payload.PostDTO;
@Service
public interface PostService {
	
	public PostDTO createPost(Post post, Integer userId, Integer categoryId);
	
	public PostDTO updatePost(Post post, Integer postId);
	
	public PostDTO getPostById(Integer postId);
	
	public List<PostDTO> getAllPosts(Integer pageNumber, Integer pageSize);
	
	public List<PostDTO> getPostByUser(Integer userId);
	
	public List<PostDTO> getPostByCategory(Integer categoryId);
	
	public List<PostDTO> serchPosts(String keyword);
	
	public void deletePost(Integer postId);

}
