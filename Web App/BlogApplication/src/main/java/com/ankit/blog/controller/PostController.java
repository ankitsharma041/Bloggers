package com.ankit.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.blog.entities.Post;
import com.ankit.blog.exception.ApiResponse;
import com.ankit.blog.payload.PostDTO;
import com.ankit.blog.services.PostService;

@RestController
@RequestMapping("api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("addPost/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDTO post = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
	}
	@GetMapping("getPost/category/{categoryId}")
	public ResponseEntity<List<PostDTO>> getByCategory(@PathVariable Integer categoryId){
		List<PostDTO> cPosts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(cPosts, HttpStatus.FOUND);
	}
	@GetMapping("getPost/user/{userId}")
	public ResponseEntity<List<PostDTO>> getByUser(@PathVariable Integer userId){
		List<PostDTO> uPosts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(uPosts, HttpStatus.FOUND);
	}
	@GetMapping("getPost/id/{postId}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Integer postId){
		PostDTO post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.FOUND);
	}
	@GetMapping("getPosts")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> allPosts = this.postService.getAllPosts();
		return new ResponseEntity<List<PostDTO>>(allPosts, HttpStatus.FOUND);
	}
	@DeleteMapping("deletePost/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post has been deleted Successfully", true);
		
	}
	@PutMapping("updatePost/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody Post post, @PathVariable Integer postId){
		PostDTO updatePost = this.postService.updatePost(post, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}
}
