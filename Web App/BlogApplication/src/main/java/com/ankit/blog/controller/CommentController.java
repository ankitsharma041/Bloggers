package com.ankit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ankit.blog.payload.ApiResponse;
import com.ankit.blog.payload.CommentDTO;
import com.ankit.blog.services.CommentService;
@RestController
@RequestMapping("api")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@PostMapping("post/{postId}/addComments")
	public ResponseEntity<CommentDTO> postComments(@RequestBody CommentDTO commentDTO,@PathVariable Integer postId){
		CommentDTO newComments = this.commentService.postComment(commentDTO, postId);
		return new ResponseEntity<CommentDTO>(newComments, HttpStatus.CREATED);
	}
	@DeleteMapping("deleteComment/{commentId}")
	public ApiResponse deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ApiResponse("Comment has benn deleted successfully", true);
	}
}
