package com.ankit.blog.services.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ankit.blog.dao.UserRepo;
import com.ankit.blog.entities.User;
import com.ankit.blog.exception.ResourceNotFoundException;
import com.ankit.blog.payload.UserDTO;
import com.ankit.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(User addUser) {
		Optional<User> dbUser = this.userRepo.findByEmail(addUser.getEmail());
		UserDTO userDTO = new UserDTO();
		if(dbUser.isPresent()) {
			userDTO = new UserDTO();
			userDTO.setMessage(addUser.getEmail()+(" already exists"));
			userDTO.setStatusCode(400);
		}else {
			
			User newUser = this.userRepo.save(addUser);	
			userDTO = this.modelMapper.map(newUser, UserDTO.class);
			userDTO.setMessage("User has been added");
			userDTO.setStatusCode(200);
		}
		
		return userDTO;
	}

	@Override
	public String deleteUser(Integer userId) {

		Optional<User> user = this.userRepo.findById(userId);
		if (user != null && user.isPresent()) {

			this.userRepo.deleteById(userId);
			return "User deleted successfully";
		} else {
			return "User not found";
		}
	}

	@Override
	public UserDTO updateUser(Integer userId, User updateUser) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		if (updateUser.getName() != null) {
			user.setName(updateUser.getName());
		}
		if (updateUser.getEmail() != null) {
			user.setEmail(updateUser.getEmail());
		}
		if (updateUser.getPassword() != null) {

			user.setPassword(updateUser.getPassword());
		}
		if (updateUser.getAbout() != null) {
			user.setAbout(updateUser.getAbout());
		}

		User updatedUser = this.userRepo.save(user);
		UserDTO userDTO = this.modelMapper.map(updatedUser, UserDTO.class);
		userDTO.setMessage("User has been updated successfully");
		userDTO.setStatusCode(200);
		userDTO.setId(updatedUser.getId());
		return userDTO;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public UserDTO getUser(Integer userId) {

		UserDTO userDTO = null;
		Optional<User> user = this.userRepo.findById(userId);
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
