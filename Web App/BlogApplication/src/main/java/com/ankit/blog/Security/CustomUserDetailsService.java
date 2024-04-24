package com.ankit.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ankit.blog.dao.UserRepo;
import com.ankit.blog.entities.User;
import com.ankit.blog.exception.ResourceNotFoundException;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from database by name
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("UserName", "email: "+username, 0));
		
		return user;
	}

}
