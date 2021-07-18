package com.marius;

import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.dto.role.RoleDTO;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.privilege.PrivilegeEnum;
import com.marius.model.domain.role.Role;
import com.marius.model.domain.role.RoleEnum;
import com.marius.model.domain.user.User;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateAuthentication {


    public UserDTO createUserDTO() {
        UserDTO userDTO = UserDTO
                .builder()
                .cityName("Krakow")
                .phoneNumber("1234")
                .userName("Roman")
                .sendMessage(true)
                .userPassword("1234abcd")
                .userNumber("123")
                .roles(Stream.of(createRoleDTO()).collect(Collectors.toSet()))
                .build();

        return userDTO;
    }

    public RoleDTO createRoleDTO() {
        RoleDTO roleDTO = RoleDTO
                .builder()
                .name(RoleEnum.ROLE_USER)
                .roleNumber("1234")
                .privileges(Stream.of(createPrivilegeDTO()).collect(Collectors.toSet()))
                .build();

        return roleDTO;
    }

    public PrivilegeDTO createPrivilegeDTO() {
        PrivilegeDTO privilegeDTO = PrivilegeDTO.builder()
                .privilegeNumber("1234Priv")
                .name(PrivilegeEnum.ADD_PRIVILEGE_PRIVILEGE)
                .build();

        return privilegeDTO;
    }

    public User createUser() {
        User user = User
                .builder()
                .cityName("Krakow")
                .phoneNumber("1234")
                .userName("Roman")
                .sendMessage(true)
                .userPassword("1234abcd")
                .userNumber("123")
                .roles(Stream.of(createRole()).collect(Collectors.toSet()))
                .build();

        return user;
    }

    public Role createRole() {
        Role role = Role
                .builder()
                .name(RoleEnum.ROLE_USER)
                .roleNumber("1234")
                .privileges(Stream.of(createPrivilege()).collect(Collectors.toSet()))
                .build();

        return role;
    }

    public Privilege createPrivilege() {
        Privilege privilege = Privilege.builder()
                .privilegeNumber("1234Priv")
                .name(PrivilegeEnum.ADD_PRIVILEGE_PRIVILEGE)
                .build();

        return privilege;
    }
}
