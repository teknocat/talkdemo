package com.example.shell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import com.example.shell.model.TalkUser;
import com.example.shell.service.UserService;

@ShellComponent
public class RestrictedCommands extends SecuredCommands {

	@Autowired
	UserService userService;	
	
    @ShellMethodAvailability("isUserSignedIn")
	@ShellMethod("Create new user with supplied username")
	public String createUser(
			@ShellOption({ "-U", "--username" }) String username,
			@ShellOption({ "-P", "--password" }) String password) {
		if (userService.exists(username)) {
			return "Error";
		};
		
		TalkUser user = new TalkUser();
		user.setUsername(username);
		user.setPassword(password);
		userService.create(user);

		return "OK";
	}    
}
