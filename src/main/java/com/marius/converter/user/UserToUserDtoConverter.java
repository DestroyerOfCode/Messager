package com.marius.converter.user;

import com.marius.converter.role.RoleConverter;
import com.marius.dto.role.RoleDTO;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.role.Role;
import com.marius.model.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserToUserDtoConverter {

    private final RoleConverter roleConverter;

    public UserToUserDtoConverter(RoleConverter roleConverter ) {
        this.roleConverter = roleConverter;
    }

    public UserDTO entityToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUserName());
        dto.setUserPassword(user.getUserPassword());
        dto.set_id(user.get_id());
        dto.setCityName(user.getCityName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setSendMessage(user.getSendMessage());

        Set<RoleDTO> roles = user.getRoles().stream().map(roleConverter::entityToDto).collect(Collectors.toSet());
        dto.setRoles(roles);

        return dto;    }

    public User dtoToEntity(UserDTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserPassword(dto.getUserPassword());
        user.set_id(dto.get_id());
        user.setCityName(dto.getCityName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setSendMessage(dto.getSendMessage());

        Set<Role> roles = dto.getRoles().stream().map(roleConverter::dtoToEntity).collect(Collectors.toSet());
        user.setRoles(roles);

        return user;
    }
}
