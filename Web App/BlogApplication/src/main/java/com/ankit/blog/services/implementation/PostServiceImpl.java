package com.ankit.blog.services.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.blog.dao.CategoryRepo;
import com.ankit.blog.dao.PostRepo;
import com.ankit.blog.dao.UserRepo;
import com.ankit.blog.entities.Category;
import com.ankit.blog.entities.Post;
import com.ankit.blog.entities.User;
import com.ankit.blog.exception.ResourceNotFoundException;
import com.ankit.blog.payload.PostDTO;
import com.ankit.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id", categoryId));
		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setDate(new Date());
		post.setImage("ankit.png");
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		
		return null;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		List<Post> post = this.postRepo.findByUser(user);
		List<PostDTO> postDTO = new ArrayList<PostDTO>();
		BeanUtils.copyProperties(post, postDTO);
		return postDTO;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> post = this.postRepo.findByCategory(category);
		List<PostDTO> postDTO = new ArrayList<PostDTO>();
		BeanUtils.copyProperties(post, postDTO);
		return postDTO;
	}

	@Override
	public List<PostDTO> serchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
