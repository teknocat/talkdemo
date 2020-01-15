package com.example.shell.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.shell.model.TalkUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MockUserService implements UserService {

	@Autowired
	private ObjectMapper objectMapper;

	private List<TalkUser> users = new ArrayList<>();

	@Override
	public boolean exists(String username) {
		for (TalkUser user : users) {
			if (username.equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TalkUser create(TalkUser user) {
		user.setId(new Long(getNextId()));
		users.add(user);
		return user;
	}

	public void init(String filePath) throws IOException {
		ClassPathResource cpr = new ClassPathResource(filePath);
		users = objectMapper.readValue(cpr.getInputStream(), new TypeReference<List<TalkUser>>() {
		});
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	private long getNextId() {
		long maxId = 0;
		for (TalkUser user : users) {
			if (user.getId().longValue() > maxId) {
				maxId = user.getId().longValue();
			}
		}
		return maxId + 1;
	}

	@Override
	public TalkUser findByUsername(String username) {
        for (TalkUser user : users) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
	}
}
