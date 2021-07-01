package com.marius.businesslogic.user;

import com.marius.converter.user.UserToUserDtoConverter;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.user.User;
import com.marius.model.repository.user.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserLogic {

    private static final Logger LOG = LoggerFactory.getLogger(UserLogic.class);

    private final UserRepository userRepository;
    private final UserToUserDtoConverter converter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserLogic(UserRepository userRepository, UserToUserDtoConverter converter,
                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    public Optional<User> getUser(ObjectId userId) {
        return userRepository.findById(userId);
    }

    public UserDTO createUser(UserDTO dto) {
        User user = converter.dtoToEntity(dto);
        user.setUserPassword(encryptUserPassword(dto.getUserPassword()));
        user = userRepository.insert(user);
        return converter.entityToDto(user);
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
