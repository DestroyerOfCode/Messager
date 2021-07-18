package com.marius.converter.user;

import com.marius.CreateAuthentication;
import com.marius.converter.role.RoleConverter;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.user.User;
import com.marius.model.repository.role.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserToUserDtoConverterTest {

    private static RoleConverter roleConverter;
    private static RoleRepository roleRepository;
    private CreateAuthentication createAuthentication;
    private UserToUserDtoConverter userToUserDtoConverter;

    @BeforeAll
    public static void setUpClass() {
        roleConverter = mock(RoleConverter.class);
        roleRepository = mock(RoleRepository.class);
    }

    @BeforeEach
    public void setUp() {
        this.userToUserDtoConverter = new UserToUserDtoConverter(roleConverter, roleRepository);
        this.createAuthentication = new CreateAuthentication();
    }

    @Test
    public void convertDTOToEntity() {
        User user = createAuthentication.createUser();
        when(roleConverter.entityToDto(createAuthentication.createRole())).thenReturn(createAuthentication.createRoleDTO());

        UserDTO userDTO = userToUserDtoConverter.entityToDto(user);

        Assertions.assertNotNull(userDTO);
    }

    @Test
    public void convertEntityToDTO() {
        UserDTO userDTO = createAuthentication.createUserDTO();
        when(roleRepository.findRoleByName(any())).thenReturn(Optional.of(createAuthentication.createRole()));

        User user = userToUserDtoConverter.dtoToEntity(userDTO);

        Assertions.assertNotNull(user);
    }

}
