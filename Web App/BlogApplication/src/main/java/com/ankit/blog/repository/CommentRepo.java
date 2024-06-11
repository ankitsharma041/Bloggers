package com.ankit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entities.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer> {

}
