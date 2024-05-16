package com.ankit.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

//	@Autowired
//	private EmailServiceImpl emailServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);

	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void emailTrigger() {
//		emailServiceImpl.sendMail("sharmankit041@gmail.com", "this is body", "Testing API", "D:\\download.jpg");
//	}

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}

}
