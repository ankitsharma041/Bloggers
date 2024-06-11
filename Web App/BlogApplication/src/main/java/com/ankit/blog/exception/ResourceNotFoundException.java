package com.ankit.blog.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String resourceName, String feildName, long feildValue) {
		super(String.format("%s not found with %s : %s", resourceName, feildName, feildValue));
		
	}
}