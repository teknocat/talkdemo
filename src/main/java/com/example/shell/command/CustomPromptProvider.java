package com.example.shell.command;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

	@Override
	public AttributedString getPrompt() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			return new AttributedString("command? " + ":>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));			
		} else {
			return new AttributedString("command? " + ":>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));			
		}
	}
}