package com.ankit.blog.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankit.blog.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	
	//User findByEmailAndPassword(String email, String password);
}
