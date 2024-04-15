package com.ankit.blog.services;

import java.util.List;

import com.ankit.blog.payload.PostsDTO;

public interface PostsService {

	PostsDTO createPosts(PostsDTO postsDto, Integer UserId, Integer CategoryId);

	PostsDTO updatePosts(PostsDTO postsDto, Integer id);

	PostsDTO getPostsById(Integer id);

	void deletePosts(Integer id);

	PostsDTO getAllPosts();

	List<PostsDTO> getPostsByCategoryId(Integer cInteger);

	List<PostsDTO> getPostsByUserId(Integer uInteger);

	List<PostsDTO> searchPosts(String keyword);
}
