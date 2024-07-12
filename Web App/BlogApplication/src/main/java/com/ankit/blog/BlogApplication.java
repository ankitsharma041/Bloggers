package com.ankit.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
