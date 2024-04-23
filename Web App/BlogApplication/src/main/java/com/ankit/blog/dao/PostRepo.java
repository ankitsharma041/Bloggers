package com.ankit.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankit.blog.entities.Category;
import com.ankit.blog.entities.Post;
import com.ankit.blog.entities.User;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

	 List<Post> findByUser(User user);
	 List<Post> findByCategory(Category category);
	 List<Post> findByTitleContaining(String title);
	 
	 
}
