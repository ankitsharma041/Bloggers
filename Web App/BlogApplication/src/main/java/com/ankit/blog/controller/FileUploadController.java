package com.ankit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ankit.blog.helper.FileUploadHelper;

@RestController
@RequestMapping("/api")
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/uploadFile")
		public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {

			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}

//			if (file.getContentType().equals("image/jpeg")) {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG format is allowed");
//			}
//			
			boolean  files = fileUploadHelper.uploadFile(file);
			if(files) {
				//return ResponseEntity.ok("File uploaded successfully");
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	}
}
