package com.ankit.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ankit.blog.dao.UserRepo;
import com.ankit.blog.entities.User;
import com.ankit.blog.exception.ResourceNotFoundException;

public class UserDetails implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "Email : "+username, 0));
		return user;
	}

}
