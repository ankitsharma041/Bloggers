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

import com.ankit.blog.entities.User;
import com.ankit.blog.payload.UserDTO;
import com.ankit.blog.services.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody User addUser) {
		UserDTO newUser = this.userService.createUser(addUser);
		return ResponseEntity.ok(newUser);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = this.userService.getAllUser();
		return ResponseEntity.ok(allUsers);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
		UserDTO getUser = this.userService.getUser(userId);
		return ResponseEntity.ok(getUser);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> udpateUser(@PathVariable Integer userId, @RequestBody User updateUser) {
		UserDTO updatedUser = this.userService.updateUser(userId, updateUser);
		return ResponseEntity.ok(updatedUser);
	}

//	@DeleteMapping("/")
//	public void deleteAllUsers() {
//		userService.deleteAllUsers();
//	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		String message = this.userService.deleteUser(userId);
		return ResponseEntity.ok(message);
	}

}
