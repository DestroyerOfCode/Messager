package com.marius.converter.user;

import com.marius.converter.role.RoleConverter;
import com.marius.dto.role.RoleDTO;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.role.Role;
import com.marius.model.domain.user.User;
import com.marius.model.repository.role.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

@Component
public class UserToUserDtoConverter {

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;

    public UserToUserDtoConverter(RoleConverter roleConverter, RoleRepository roleRepository ) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
    }

    public Stack<Integer> foo(){
        Stack<Integer> s = new Stack<>();
        s.push(2);
        return s;
    }
    public UserDTO entityToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUserName());
        dto.setUserPassword(user.getUserPassword());
        dto.set_id(user.get_id());
        dto.setCityName(user.getCityName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setSendMessage(user.getSendMessage());
        dto.setUserNumber(user.getUserNumber());
        dto.setEmail(user.getEmail());

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
        user.setUserNumber(dto.getUserNumber());
        user.setEmail(dto.getEmail());

        Set<Role> roles = dto.getRoles().stream()
                .map((RoleDTO roleDTO) -> roleRepository.findRoleByName(roleDTO.getName()).orElseThrow())
                .collect(Collectors.toSet());

        user.setRoles(roles);

        return user;
    }
}
