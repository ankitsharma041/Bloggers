package com.ankit.blog.services.implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ankit.blog.entities.User;
import com.ankit.blog.services.SecurityTokenGenerator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generateToken(User user) {
		String token = null;
        Map<String,String> result= new HashMap<>();
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("role_user",user.getRole());
        userdata.put("user_email",user.getEmail());

        token = Jwts.builder()
                .setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mykey").compact();

        result.put("token",token);
        result.put("email",user.getEmail());
        result.put("message","User successfully logged in");
        result.put("role",user.getRole());
        return result;
	}

}
