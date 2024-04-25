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
import com.ankit.blog.Security.JwtTokenHelper;
import com.ankit.blog.payload.JwtAuthRequest;
import com.ankit.blog.payload.JwtAuthResponse;

@RestController
	@RequestMapping("/api/auth/")
	public class AuthController {

		@Autowired
		private JwtTokenHelper jwtHelper;

		@Autowired
		private UserDetailsService userDetailsService;

		@Autowired
		private AuthenticationManager authenticationManager;

		@PostMapping("login")
		public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
			this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
			String generateToken = this.jwtHelper.generateToken(userDetails);
			JwtAuthResponse response = new JwtAuthResponse();
			response.setToken(generateToken);
			return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		}

		private void authenticate(String username, String password) throws Exception {
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					username, password);

			try {
				this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

			} catch (BadCredentialsException e) {
				
				System.out.println("Invalid user details!");
				throw new Exception("Invalid user details!");
			}

		}
}
