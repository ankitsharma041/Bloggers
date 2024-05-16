package com.ankit.blog.services;

public interface EmailService {

	public void sendMail(String toEmail, String body, String subject, byte[] attachmentData, String attachmentName);
}
