package com.example.shell.service;

import com.example.shell.model.TalkUser;

public interface UserService {
	TalkUser create(TalkUser user);
	TalkUser findByUsername(String username);
	boolean exists(String username);
}
