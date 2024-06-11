package com.ankit.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.User;
import com.ankit.blog.payload.UserDTO;
import com.ankit.blog.requestDTO.UserRequestDTO;
import com.ankit.blog.responseDTO.UserResponseDTO;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface UserService {

	public UserResponseDTO createUser(UserRequestDTO userRequestDTO);

	public UserDTO updateUser(Integer userId, User updateUser);

	public UserDTO deleteUser(Integer userId);

	public List<User> getAllUser();

	public UserDTO getUser(Integer userId);

	public void generateExcel(HttpServletResponse response);
	
	
}
