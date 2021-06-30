package com.marius.businesslogic.user;

import com.marius.converter.user.UserToUserDtoConverter;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.user.User;
import com.marius.model.repository.user.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserLogic {

    private final UserRepository userRepository;
    private final UserToUserDtoConverter converter;

    @Autowired
    public UserLogic(UserRepository userRepository, UserToUserDtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
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
        user = userRepository.insert(user);
        return converter.entityToDto(user);
    }
}
