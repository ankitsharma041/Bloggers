package com.ankit.blog.services.implementation;

import java.util.List;

import com.ankit.blog.payload.PostsDTO;
import com.ankit.blog.services.PostsService;

public class PostsServiceImpl implements PostsService {

	@Override
	public PostsDTO createPosts(PostsDTO postsDto, Integer UserId, Integer CategoryId) {
		
		return null;
	}

	@Override
	public PostsDTO updatePosts(PostsDTO postsDto, Integer id) {
		
		return null;
	}

	@Override
	public PostsDTO getPostsById(Integer id) {
		
		return null;
	}

	@Override
	public void deletePosts(Integer id) {
		

	}

	@Override
	public PostsDTO getAllPosts() {
		
		return null;
	}

	@Override
	public List<PostsDTO> getPostsByCategoryId(Integer cInteger) {
		
		return null;
	}

	@Override
	public List<PostsDTO> getPostsByUserId(Integer uInteger) {
		
		return null;
	}

	@Override
	public List<PostsDTO> searchPosts(String keyword) {
		
		return null;
	}

}
