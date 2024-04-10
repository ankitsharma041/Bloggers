package com.ankit.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.Category;
import com.ankit.blog.payload.CategoryDTO;
@Service
public interface CategoryService {

	public CategoryDTO addCategory(Category category);
	
	public CategoryDTO updateCategory(Category category, Integer categoryId);
	
	public String deleteCategory(Integer categoryId);
	
	Optional<Category> getCategory(Integer category);
	
	public List<Category> getCategories();
}
