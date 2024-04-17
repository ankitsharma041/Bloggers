package com.ankit.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankit.blog.entities.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	Category findByTitle(String title);
}
