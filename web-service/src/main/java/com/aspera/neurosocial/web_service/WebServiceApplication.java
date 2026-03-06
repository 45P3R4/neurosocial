package com.aspera.neurosocial.web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebServiceApplication.class, args);
	}
}
