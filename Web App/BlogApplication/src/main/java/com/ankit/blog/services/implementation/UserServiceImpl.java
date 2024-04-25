package com.ankit.blog.services.implementation;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
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
		if (dbUser.isPresent()) {
			userDTO.setMessage(addUser.getEmail() + (" already exists"));
			userDTO.setStatusCode(400);
		} else {

			User newUser = this.userRepo.save(addUser);
			userDTO = this.modelMapper.map(newUser, UserDTO.class);
			userDTO.setMessage("Your UserId is "+newUser.getId());
			userDTO.setStatusCode(200);
		}

		return userDTO;
	}

	@Override
	public UserDTO deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		 this.userRepo.delete(user);
		 UserDTO userDTO = new UserDTO();
		 userDTO.setMessage("User with username " +user.getEmail()+ " has been deleted");
		 userDTO.setStatusCode(200);
		 return userDTO;
	}

	@Override
	public UserDTO updateUser(Integer userId, User updateUser) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		if (updateUser.getName() != null) {
			user.setName(updateUser.getName());
		}
//		if (updateUser.getEmail() != null) {
//			user.setEmail(updateUser.getEmail());
//		}
		if (updateUser.getPassword() != null) {

			user.setPassword(updateUser.getPassword());
		}
		if (updateUser.getAbout() != null) {
			user.setAbout(updateUser.getAbout());
		}

		User updatedUser = this.userRepo.save(user);
		UserDTO userDTO = this.modelMapper.map(updatedUser, UserDTO.class);
		userDTO.setMessage("User details has been updated successfully");
		userDTO.setStatusCode(200);
		return userDTO;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public UserDTO getUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		userDTO.setMessage("User with userId "+user.getId()+" is "+user.getName());
		userDTO.setStatusCode(200);
		return userDTO;
	}
}
