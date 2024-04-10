package com.ankit.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.User;
import com.ankit.blog.payload.UserDTO;
@Service
public interface UserService {

	public UserDTO createUser(User addUser);
	
	public UserDTO updateUser(Integer userId, User updateUser);
	
	public String deleteUser(Integer userId);
	
//	public void deleteAllUsers();
	
	public List<User> getAllUser();
	
	public Optional<User> getUser(Integer userId);
	}
