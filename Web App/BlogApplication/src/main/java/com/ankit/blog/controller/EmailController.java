package com.ankit.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ankit.blog.services.implementation.EmailServiceImpl;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String toEmail,
            @RequestParam String body,
            @RequestParam String subject,
            @RequestParam("files") MultipartFile[] files) {

        try {
            byte[] zipData = emailService.createZipFile(files);
            emailService.sendMail(toEmail, body, subject, zipData, "attachment.zip");
            return ResponseEntity.ok("Email has been sent successfully");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error while sending email: " + e.getMessage());
        }
    }
}
