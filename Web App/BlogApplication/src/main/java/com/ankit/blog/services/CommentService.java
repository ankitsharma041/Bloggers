package com.ankit.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.Comments;
import com.ankit.blog.payload.CommentDTO;

@Service
public interface CommentService {

	public CommentDTO postComment(CommentDTO commentDTO, Integer postId);
	public void deleteComment(Integer commentId);
	public CommentDTO updateComment(CommentDTO commentDTO, Integer commentId);
	public List<Comments> getComments();
}
