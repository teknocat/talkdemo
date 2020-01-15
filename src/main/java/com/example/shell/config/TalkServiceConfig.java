package com.example.shell.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.shell.service.MockTalkService;
import com.example.shell.service.TalkService;

@Configuration
public class TalkServiceConfig {

	@Bean
	public TalkService talkService() throws IOException {
		MockTalkService talkService = new MockTalkService();
		talkService.init();
		return talkService;
	}
}