package com.marius.service.user;

import com.marius.model.domain.user.CustomUserDetails;
import com.marius.model.domain.user.User;
import com.marius.model.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByUserName(userName);
        if (user.isPresent())
            return new CustomUserDetails(user.get());
        throw new UsernameNotFoundException(String.format("User %s not present!", userName));

    }
}