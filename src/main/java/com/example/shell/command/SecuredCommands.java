package com.example.shell.command;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.Availability;

public abstract class SecuredCommands {

	public Availability isUserSignedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)) {
			return Availability.unavailable("you are not signedIn. Please sign in to be able to use this command!");
		}
		return Availability.available();
	}
}
