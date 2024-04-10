package com.ankit.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.Category;
@Service
public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category, Integer categoryId);
	
	public String deleteCategory(Integer categoryId);
	
	Optional<Category> getCategory(Integer category);
	
	public List<Category> getCategories();
}
