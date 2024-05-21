package com.ankit.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.User;
import com.ankit.blog.payload.UserDTO;

@Service
public interface UserService {

	public UserDTO createUser(User addUser);

	public UserDTO updateUser(Integer userId, User updateUser);

	public UserDTO deleteUser(Integer userId);

	public List<User> getAllUser();

	public UserDTO getUser(Integer userId);
	
	public  User loginCheck(String email , String userPassword) throws Exception;
}
