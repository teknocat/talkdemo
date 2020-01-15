package com.example.shell.service;

import java.util.List;

import com.example.shell.model.Talk;

public interface TalkService {
	Talk create(Talk talk);

	List<Talk> findAllTalks();

	String reply();

	void talk(String message);

	List<Talk> findAllPresets();
}
