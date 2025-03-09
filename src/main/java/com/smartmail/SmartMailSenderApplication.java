package com.smartmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc

public class SmartMailSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMailSenderApplication.class, args);
		
	}

}
