package com.hexaware;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hexaware")
public class FastxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastxApplication.class, args);
		
	}
	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	

}
