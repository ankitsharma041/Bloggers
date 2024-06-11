package com.ankit.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.ankit.blog.requestDTO.UserRequestDTO;
import com.ankit.blog.responseDTO.UserResponseDTO;
import com.ankit.blog.services.SecurityTokenGenerator;
import com.ankit.blog.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;

	@PostMapping("/addUser")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO newUser = this.userService.createUser(userRequestDTO);
		return new ResponseEntity<UserResponseDTO>(newUser, HttpStatus.OK);
	}

	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = this.userService.getAllUser();
		return ResponseEntity.ok(allUsers);
	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
		UserDTO getUser = this.userService.getUser(userId);
		return ResponseEntity.ok(getUser);
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDTO> udpateUser(@PathVariable Integer userId, @RequestBody User updateUser) {
		UserDTO updatedUser = this.userService.updateUser(userId, updateUser);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer userId) {

		UserDTO deletedUser = this.userService.deleteUser(userId);
		return new ResponseEntity<UserDTO>(deletedUser, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=UserDetails.xls";

		response.setHeader(headerKey, headerValue);

		userService.generateExcel(response);

		response.flushBuffer();
	}

}
