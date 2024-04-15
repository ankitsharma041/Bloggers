package com.ankit.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entities.Category;
import com.ankit.blog.entities.Posts;
import com.ankit.blog.entities.User;

public interface PostsRepo extends JpaRepository<Posts, Integer> {

	List<Posts> findByUser(User user);

	List<Posts> findByCategory(Category category);
}
