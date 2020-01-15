package com.example.shell;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShellApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ShellApplication.class);
//		app.setWebApplicationType(WebApplicationType.NONE);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
