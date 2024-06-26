package com.ankit.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankit.blog.entities.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	Optional<Category> findByTitle(String title);
}
