package com.example.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class WelcomeCommands {

	@ShellMethod("Hello World")
	public void hello() {
		System.out.println("Hello Talk Demo!!");
	}
}
