package com.ankit.blog.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ankit.blog.dao.UserRepository;
import com.ankit.blog.entities.User;
import com.ankit.blog.payload.UserDTO;
import com.ankit.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(User addUser) {
		UserDTO userDTO = null;
		User existingUser = new User();
		if (existingUser != null) {
			User dbUser = this.userRepository.findByEmail(addUser.getEmail());
			if (dbUser != null) {
				userDTO = new UserDTO();
				BeanUtils.copyProperties(existingUser, userDTO);
				userDTO.setMessage("User already exist");
				userDTO.setStatusCode(400);
//				try {
//					throw new Exception("User already exists !");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			} else {
				User savedUser = this.userRepository.save(addUser);
				userDTO = new UserDTO();
				BeanUtils.copyProperties(savedUser, userDTO);
				userDTO.setMessage("Successfully saved");
				userDTO.setStatusCode(200);
				// return userDTO;
			}
		}
		return userDTO;
	}

	@Override
	public String deleteUser(Integer userId) {

		Optional<User> user = this.userRepository.findById(userId);
		if (user != null && user.isPresent()) {

			this.userRepository.deleteById(userId);
			return "User deleted successfully";
		} else {
			return "User not found";
		}
	}

//	@Override
//	public void deleteAllUsers() {
//		userRepository.deleteAll();
//
//	}

	@Override
	public UserDTO updateUser(Integer userId, User updateUser) {
		UserDTO userDTO = null;
		Optional<User> existingUser = this.userRepository.findById(userId);
		if (existingUser.isPresent()) {
			User user = existingUser.get();
			user.setName(updateUser.getName());
			user.setEmail(updateUser.getEmail());
			user.setPassword(updateUser.getPassword());
			user.setAbout(updateUser.getAbout());

			User updatedUser = this.userRepository.save(user);
			userDTO = new UserDTO();
			BeanUtils.copyProperties(updatedUser, userDTO);
			userDTO.setMessage("User updated Successfully");
			userDTO.setStatusCode(200);
			return userDTO;
		} else {
			userDTO = new UserDTO();
			userDTO.setMessage("User Not Found");
			// throw new RuntimeException("User not Found");
			userDTO.setStatusCode(404);
		}
		return userDTO;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Override
	public UserDTO getUser(Integer userId) {

		UserDTO userDTO = null;
		Optional<User> user = this.userRepository.findById(userId);
		if (user.isPresent()) {
			User user2 = user.get();			
			userDTO = new UserDTO();
			BeanUtils.copyProperties(user2, userDTO);
			userDTO.setMessage("User Found");
			userDTO.setStatusCode(200);
		} else {
			userDTO = new UserDTO();
			userDTO.setMessage("User Not Found");
			userDTO.setStatusCode(404);
		}
		return userDTO;

	}

}
