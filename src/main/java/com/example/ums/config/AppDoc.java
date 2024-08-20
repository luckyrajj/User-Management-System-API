package com.example.ums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
@OpenAPIDefinition
public class AppDoc {

	Info info() {
		return new Info().title("User-Management-System Restful-API").version("v1")
				.description("A simple CRUD API managing user data,built on **Rest Architectural Style**");
	}
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
}
