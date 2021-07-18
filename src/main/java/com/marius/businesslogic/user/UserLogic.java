package com.marius.businesslogic.user;

import com.marius.converter.user.UserToUserDtoConverter;
import com.marius.dto.jwt.JwtResponse;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.user.CustomUserDetails;
import com.marius.model.domain.user.User;
import com.marius.model.repository.user.UserRepository;
import com.marius.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserLogic {

    private static final Logger LOG = LoggerFactory.getLogger(UserLogic.class);

    private final UserRepository userRepository;
    private final UserToUserDtoConverter converter;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserLogic(UserRepository userRepository, UserToUserDtoConverter converter,
                     AuthenticationManager authenticationManager,
                     PasswordEncoder passwordEncoder,
                     JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return Optional.of(users)
                .orElseThrow()
                .stream()
                    .map(converter::entityToDto)
                    .collect(Collectors.toList());
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    public UserDTO createUser(UserDTO dto) {
        userRepository.getUserByUserName(dto.getUserName()).ifPresentOrElse((User presentUser) -> {
            LOG.error("user {} already present in database. please pick another user", presentUser.getUserName());
            throw new RuntimeException(String.format("user %s already present in database. please pick another user",
                    presentUser.getUserName()));
        }, () -> {
            User user = converter.dtoToEntity(dto);
            user.setUserPassword(encryptUserPassword(dto.getUserPassword()));
            userRepository.insert(user);
        });

        return dto;
    }

    public JwtResponse login(Map<String, String> credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.get("userName"), credentials.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return JwtResponse.builder()
                .jwt(jwt)
                .id(userDetails.getUser().get_id())
                .userName(userDetails.getUser().getUserName())
                .phoneNumber(userDetails.getUser().getPhoneNumber())
                .roles(userDetails.getUser().getRoles())
                .build();
    }

    private String encryptUserPassword(String plainTextPassword) {
        String encodedPassword = "";
        try {
            encodedPassword = passwordEncoder.encode(plainTextPassword);
            return encodedPassword;
        } catch (IllegalArgumentException e) {
            LOG.error("Could not encode password", e);
        }
        throw new RuntimeException("error encrypting password");
    }
}
