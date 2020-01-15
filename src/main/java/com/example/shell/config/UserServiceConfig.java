package com.example.shell.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.shell.service.MockUserService;
import com.example.shell.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UserServiceConfig {

	@Bean
	public UserService userService(ObjectMapper objectMapper) throws IOException {
		MockUserService userService = new MockUserService();
		userService.setObjectMapper(objectMapper);
		userService.init("talk-users.json");
		return userService;
	}
}