package com.ankit.blog.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public PostDTO createPost(Post post, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		Post cPost = new Post();
		cPost.setPostTitle(post.getPostTitle());
		cPost.setImage("test.png");
		cPost.setContent(post.getContent());
		cPost.setDate(new Date());
		cPost.setUser(user);
		cPost.setCategory(category);
		cPost.setPostId(post.getPostId());
		
		Post newPost = this.postRepo.save(cPost);
		PostDTO postDTO = this.modelMapper.map(newPost, PostDTO.class);
		postDTO.setMessage("Post Created Successfully");
		
		
		return postDTO;
		
	}

	@Override
	public PostDTO updatePost(Post post, Integer postId) {
		Post uPost = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		if(post.getPostTitle() != null) {
			uPost.setPostTitle(post.getPostTitle());
		}
		if(post.getContent() != null) {
			uPost.setContent(post.getContent());
		}
		if(post.getImage() != null) {
			uPost.setImage(post.getImage());
		}
		
		Post updatePost = this.postRepo.save(uPost);
		PostDTO postDTO = this.modelMapper.map(updatePost, PostDTO.class);
		postDTO.setMessage("Post has been Updated..");
		return postDTO;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post getPost = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		PostDTO postDTO = this.modelMapper.map(getPost, PostDTO.class);
		return postDTO;
	}

	@Override
	public List<PostDTO> getAllPosts(Integer pageNumber, Integer pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = this.postRepo.findAll(pageable);
		List<Post> getAllPosts =  pagePost.getContent();                     //this.postRepo.findAll();
		List<PostDTO> postDTO = getAllPosts.stream().map((posts) -> this.modelMapper.map(posts, PostDTO.class))
				.collect(Collectors.toList());
		return postDTO;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDTO> postDTO = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDTO;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDTO> postDTO = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDTO;
	}

	@Override
	public List<PostDTO> serchPosts(String keyword) {
		
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		Post dPost = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		this.postRepo.delete(dPost);
	}

}
