package com.marius.converter.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marius.dto.UserDTO;
import com.marius.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter {

    private final ObjectMapper objectMapper;

    public UserToUserDtoConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public UserDTO entityToDto(User user) {
        return objectMapper.convertValue(user, UserDTO.class);
    }

    public User dtoToEntity(UserDTO dto) {
        return objectMapper.convertValue(dto, User.class);
    }
}
