package com.ankit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ankit.blog.security.JwtHelper;
import com.ankit.blog.security.JwtRequest;
import com.ankit.blog.security.JwtResponse;
import com.ankit.blog.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) throws Exception{
	
		this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtHelper.generateToken(userDetails);
		
		JwtResponse jwtResponse = JwtResponse.builder()
								.jwtToken(token)
								.username(userDetails.getUsername()).build();
		jwtResponse.setJwtToken(token);
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}
	
	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);

		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		} catch (BadCredentialsException e) {
		 
			throw new Exception("Invalid user details!");
		}

	}
}
