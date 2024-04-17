package com.ankit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ankit.blog.payload.PostDTO;
import com.ankit.blog.services.PostService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api")
public class PostController {
	@Autowired
	private PostService postService;
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDTO postsDtoRes = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(postsDtoRes, HttpStatus.CREATED);
	}
	@PutMapping("/user/{userId}/category/{categoryId}/post/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId){
		PostDTO updatedPost = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);
		
	}
	@GetMapping("/user/{userId}/category/{categoryId}/post/{postId}")
	public ResponseEntity<PostDTO> getPostsById(@PathVariable Integer postId){
		PostDTO getPost = this.postService.getPostsById(postId);
		return new ResponseEntity<PostDTO>(getPost, HttpStatus.FOUND);
	}
	@DeleteMapping("/user/{userId}/category/{categoryId}/post/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<String>("Post deleted!", HttpStatus.OK);
	}
	@GetMapping("/user/{userId}/category/{categoryId}/post/")
	public ResponseEntity<PostDTO> getAllPosts(){
		PostDTO getAllPost = this.postService.getAllPost();
		return new ResponseEntity<PostDTO>(getAllPost, HttpStatus.FOUND);
	}
}
