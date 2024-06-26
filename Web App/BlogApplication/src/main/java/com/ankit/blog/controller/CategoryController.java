package com.ankit.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.blog.entities.Category;
import com.ankit.blog.payload.CategoryDTO;
import com.ankit.blog.services.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
		
	@PostMapping("/addCategory")
	    public ResponseEntity<CategoryDTO> addCategory(@RequestBody Category category){
	    	CategoryDTO addCategory = this.categoryService.addCategory(category);
			return ResponseEntity.ok(addCategory);
	    	
	    }
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody Category category, @PathVariable Integer categoryId){
		CategoryDTO updateCategory = this.categoryService.updateCategory(category, categoryId);
		return ResponseEntity.ok(updateCategory);
	}
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Integer categoryId){
		CategoryDTO deleteCategory = this.categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok(deleteCategory);
	}
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
		CategoryDTO getCategory = this.categoryService.getCategory(categoryId);
		return ResponseEntity.ok(getCategory);
	}
	@GetMapping("/getCategories")
	public ResponseEntity<List<Category>> getCategories(){
		List<Category> getAll = this.categoryService.getCategories();
		return ResponseEntity.ok(getAll);
	}

}
