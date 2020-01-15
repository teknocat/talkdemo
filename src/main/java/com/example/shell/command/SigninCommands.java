package com.example.shell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class SigninCommands extends SecuredCommands {

    @Autowired
    AuthenticationManager authenticationManager;

    @ShellMethod("Sign in as talk user")
    public String signin(
			@ShellOption({ "-U", "--username" }) String username,
			@ShellOption({ "-P", "--password" }) String password) {    		
        Authentication request = new UsernamePasswordAuthenticationToken(username, password);

        try {
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return "ようこそ " + username + " さん";
        } catch (AuthenticationException e) {
            return "Authentication failed: " + e.getMessage();
        }
    }
}