package com.ankit.blog.services;

import java.util.Map;

import com.ankit.blog.entities.User;

public interface SecurityTokenGenerator {
	 public abstract Map<String, String> generateToken(User user);
}





