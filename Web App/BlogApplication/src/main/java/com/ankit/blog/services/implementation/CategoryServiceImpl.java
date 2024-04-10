package com.ankit.blog.services.implementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ankit.blog.dao.CategoryRepository;
import com.ankit.blog.entities.Category;
import com.ankit.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		Category existingCategory = new Category();
		if(existingCategory != null) {
			Category dbCategory = this.categoryRepository.findById(category.getCategoryId());
			if(dbCategory != null) {
				try {
					throw new Exception("Category already exists !");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				Category saveCategory = this.categoryRepository.save(category);
				return saveCategory;
			}
		}
		return null;
	}

	@Override
	public Category updateCategory(Category category, Integer categoryId) {
		Optional<Category> existingCategory = this.categoryRepository.findById(categoryId);
		if (existingCategory.isPresent()) {
			Category dbCategory = existingCategory.get();
			dbCategory.setTitle(category.getTitle());
			return this.categoryRepository.save(dbCategory);
		} else {
			throw new RuntimeException("Category not Found");
		}
	}

	@Override
	public String deleteCategory(Integer categoryId) {
		Optional<Category> category = this.categoryRepository.findById(categoryId);
		if (category != null && category.isPresent()) {
			this.categoryRepository.deleteById(categoryId);
			return "Category deleted successfully";
		} else {
			return "Category not found";
		}
		
	}

	@Override
	public Optional<Category> getCategory(Integer category) {
		Optional<Category> getCategory = this.categoryRepository.findById(category);
		return getCategory;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> getAll = categoryRepository.findAll();
		return getAll;
	}

	
	
}
