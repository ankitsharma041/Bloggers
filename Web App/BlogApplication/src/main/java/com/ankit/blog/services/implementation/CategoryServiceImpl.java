package com.ankit.blog.services.implementation;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ankit.blog.dao.CategoryRepo;
import com.ankit.blog.entities.Category;
import com.ankit.blog.exception.ResourceNotFoundException;
import com.ankit.blog.payload.CategoryDTO;
import com.ankit.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO addCategory(Category category) {
		Optional<Category> dbCategory = this.categoryRepo.findByTitle(category.getTitle());
		CategoryDTO categoryDTO = new CategoryDTO();
		if(dbCategory.isPresent()) {
			categoryDTO.setMessage(category.getTitle()+" already exists!..");
		}else {
			Category newCategory = this.categoryRepo.save(category);
			categoryDTO = this.modelMapper.map(newCategory, CategoryDTO.class);
			categoryDTO.setMessage("CategoryId :- "+newCategory.getId());
			categoryDTO.setStatusCode(200);
		}
		return categoryDTO;
			
		
	}

	@Override
	public CategoryDTO updateCategory(Category category, Integer categoryId) {
		Category uCategory = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		if(category.getTitle() != null) {
			uCategory.setTitle(category.getTitle());
		}
		if(category.getDescription() != null) {
			uCategory.setDescription(category.getDescription());
		}
		
		Category updatedCategory = this.categoryRepo.save(uCategory);
		CategoryDTO categoryDTO = this.modelMapper.map(updatedCategory, CategoryDTO.class);
		categoryDTO.setMessage("Category has been Updated successfully");
		categoryDTO.setStatusCode(200);
		
		return categoryDTO;
	}

	@Override
	public CategoryDTO deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		this.categoryRepo.delete(category);
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setMessage("Category with id "+category.getId()+"has been deleted..");
		categoryDTO.setStatusCode(200);
		return categoryDTO;
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		CategoryDTO categoryDTO =this.modelMapper.map(category, CategoryDTO.class);
		categoryDTO.setMessage("Category with categoryId "+category.getId()+" and Category title is "+category.getTitle());
		categoryDTO.setStatusCode(200);

		return categoryDTO;

	}

	@Override
	public List<Category> getCategories() {
		List<Category> getAll = categoryRepo.findAll();
		return getAll;
	}

}
