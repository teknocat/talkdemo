package com.example.shell.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import com.example.shell.model.Talk;
import com.example.shell.service.TalkService;

@ShellComponent
public class TalkCommands extends SecuredCommands {

	@Autowired
	TalkService talkService;	
	
    @ShellMethod("Talk with me")
    @ShellMethodAvailability("isUserSignedIn")
    public void talk(String message) {
    	talkService.talk(message);
    	
    	String reply = talkService.reply();

    	System.out.println(AnsiOutput.toString(AnsiColor.GREEN, reply, AnsiColor.DEFAULT));
    }

    @ShellMethod("List talk massages")
    @ShellMethodAvailability("isUserSignedIn")
    public void listMessages() {
    	List<Talk> talks = talkService.findAllTalks();
		System.out.println("--- talks ---");
		for (Talk talk : talks) {
			System.out.format("%d: %s / %s\n", talk.getId(), talk.getMessage(), talk.getAnswer());
		}    	
    	List<Talk> presets = talkService.findAllPresets();
		System.out.println("--- presets ---");
		for (Talk talk : presets) {
			System.out.format("%d: %s\n", talk.getId(), talk.getMessage());
		}
    }
}
