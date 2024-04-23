package com.ankit.blog.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.blog.dao.CommentRepo;
import com.ankit.blog.dao.PostRepo;
import com.ankit.blog.entities.Comments;
import com.ankit.blog.entities.Post;
import com.ankit.blog.exception.ResourceNotFoundException;
import com.ankit.blog.payload.CommentDTO;
import com.ankit.blog.services.CommentService;
@Service
public class CommentImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO postComment(CommentDTO commentDTO, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comments comments = this.modelMapper.map(commentDTO, Comments.class);
		comments.setPost(post);
		Comments newComment = this.commentRepo.save(comments);
		CommentDTO commentDTOs = this.modelMapper.map(newComment, CommentDTO.class);
		return commentDTOs;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comments comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
		this.commentRepo.delete(comment);
	}

	@Override
	public CommentDTO updateComment(CommentDTO commentDTO, Integer commentId) {
		Comments comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
		if(comment.getContent() != null) {
		comment.setContent(commentDTO.getContent());
		}
		CommentDTO commentDTOs = this.modelMapper.map(comment, CommentDTO.class);
		commentDTOs.setMessage("Comment has been Updated");
		return commentDTOs;
	}

}
