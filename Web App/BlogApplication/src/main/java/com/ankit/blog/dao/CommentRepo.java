package com.ankit.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entities.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer> {

}
