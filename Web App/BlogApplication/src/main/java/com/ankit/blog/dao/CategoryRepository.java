package com.ankit.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankit.blog.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findById(int categoryId);
}
