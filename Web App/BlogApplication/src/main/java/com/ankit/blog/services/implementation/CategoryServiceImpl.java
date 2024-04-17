package com.ankit.blog.services.implementation;

import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.blog.dao.CategoryRepo;
import com.ankit.blog.entities.Category;
import com.ankit.blog.payload.CategoryDTO;
import com.ankit.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDTO addCategory(Category category) {
		CategoryDTO categoryDTO = null;
		Category existingCategory = new Category();
		if (existingCategory != null) {
			Category dbCategory = this.categoryRepo.findByTitle(category.getTitle());
			if (dbCategory != null) {
				try {
					categoryDTO = new CategoryDTO();
					categoryDTO.setCategoryId(dbCategory.getCategoryId());
					categoryDTO.setTitle(dbCategory.getTitle());
					categoryDTO.setDescription(dbCategory.getDescription());
					BeanUtils.copyProperties(existingCategory, categoryDTO);
					categoryDTO.setMessage("Category already exist");
					categoryDTO.setStatusCode(400);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				Category saveCategory = this.categoryRepo.save(category);
				try {
					categoryDTO = new CategoryDTO();
					categoryDTO.setCategoryId(saveCategory.getCategoryId());
					categoryDTO.setTitle(saveCategory.getTitle());
					categoryDTO.setDescription(saveCategory.getDescription());
					BeanUtils.copyProperties(saveCategory, categoryDTO);
					categoryDTO.setMessage("Category successfully added");
					categoryDTO.setStatusCode(200);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return categoryDTO;
	}

	@Override
	public CategoryDTO updateCategory(Category category, Integer categoryId) {
		CategoryDTO categoryDTO = null;
		Optional<Category> existingCategory = this.categoryRepo.findById(categoryId);
		if (existingCategory.isPresent()) {
			Category dbCategory = existingCategory.get();
			dbCategory.setTitle(category.getTitle());
			Category updatedCategory = this.categoryRepo.save(dbCategory);
			try {
				categoryDTO = new CategoryDTO();
				categoryDTO.setCategoryId(updatedCategory.getCategoryId());
				categoryDTO.setTitle(updatedCategory.getTitle());
				categoryDTO.setDescription(updatedCategory.getDescription());
				BeanUtils.copyProperties(updatedCategory, categoryDTO);
				categoryDTO.setMessage("Category updated Successfully");
				categoryDTO.setStatusCode(200);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			// throw new RuntimeException("Category not Found");
			categoryDTO = new CategoryDTO();
			categoryDTO.setMessage("Category not Found");
			categoryDTO.setStatusCode(404);
		}
		return categoryDTO;
	}

	@Override
	public String deleteCategory(Integer categoryId) {
		Optional<Category> category = this.categoryRepo.findById(categoryId);
		if (category != null && category.isPresent()) {
			this.categoryRepo.deleteById(categoryId);
			return "Category deleted successfully";
		} else {
			return "Category not found";
		}

	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {

		CategoryDTO categoryDTO = null;
		Optional<Category> category = this.categoryRepo.findById(categoryId);
		if (category.isPresent()) {
			Category foundCategory = category.get();
			//System.out.println(foundCategory.getTitle());
			categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryId(foundCategory.getCategoryId());
			categoryDTO.setDescription(foundCategory.getDescription());
			categoryDTO.setTitle(foundCategory.getTitle());
			categoryDTO.setMessage("Category Found");
			categoryDTO.setStatusCode(200);
		} else {
			categoryDTO = new CategoryDTO();
			categoryDTO.setTitle("Not Exist");
			categoryDTO.setMessage("Category Not Found");
			categoryDTO.setStatusCode(404);
		}
		return categoryDTO;

	}

	@Override
	public List<Category> getCategories() {
		List<Category> getAll = categoryRepo.findAll();
		return getAll;
	}

}
