package com.example.shell.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.shell.model.TalkUser;
import com.example.shell.service.UserService;

public class TalkUserDetailsService implements UserDetailsService {

    private UserService userService;

    public TalkUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TalkUser talkUser = userService.findByUsername(username);

        if (talkUser == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        User.UserBuilder builder = User.withUsername(username);
        builder.password(new BCryptPasswordEncoder().encode(talkUser.getPassword()));
        builder.roles("USER");

        return builder.build();
    }
}