package com.ankit.blog.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	private ModelMapper mapper;
	
	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Category category = categoryRepo.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("category","id", categoryId));

		Post post = this.mapper.map(postDTO, Post.class);
		post.setImage("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post resPost = this.postRepo.save(post);
		

		return this.mapper.map(resPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer id) {
		Post post = this.postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		post.setImage(postDTO.getImage());
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		Post updatePost = this.postRepo.save(post);
		return this.mapper.map(updatePost, PostDTO.class);
	}

	@Override
	public PostDTO getPostsById(Integer id) {
		
		Post post = this.postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		return this.mapper.map(post, PostDTO.class);
	}

	@Override
	public void deletePost(Integer id) {
		Post post = this.postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		 this.postRepo.delete(post);
	
	}

	@Override
	public PostDTO getAllPost() {
		List<Post> post = this.postRepo.findAll();
		return this.mapper.map(post,PostDTO.class);
		
	}

	@Override
	public List<PostDTO> getPostsByCategoryId(Integer cInteger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getPostsByUserId(Integer uInteger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
