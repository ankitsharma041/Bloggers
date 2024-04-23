package com.ankit.blog.services;

import org.springframework.stereotype.Service;

import com.ankit.blog.payload.CommentDTO;

@Service
public interface CommentService {

	public CommentDTO postComment(CommentDTO commentDTO, Integer postId);
	public void deleteComment(Integer commentId);
	public CommentDTO updateComment(CommentDTO commentDTO, Integer commentId);
}
