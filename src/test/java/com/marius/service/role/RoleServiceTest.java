package com.marius.service.role;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marius.CreateAuthentication;
import com.marius.businesslogic.role.RoleLogic;
import com.marius.dto.role.RoleDTO;
import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.role.Role;
import com.marius.model.domain.role.RoleEnum;
import com.marius.model.repository.role.RoleRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    private static RoleLogic roleLogic;
    private static ObjectMapper objectMapper;
    private static RoleRepository roleRepository;
    private RoleService roleService;
    private CreateAuthentication createAuthentication;

    @BeforeAll
    public static void setUpClass() {
        roleLogic = mock(RoleLogic.class);
        objectMapper = spy(ObjectMapper.class);
        roleRepository = mock(RoleRepository.class);
    }

    @BeforeEach
    public void setUp() {
        this.roleService = new RoleService(roleLogic, objectMapper, roleRepository);
        this.createAuthentication = new CreateAuthentication();
    }

    @Test
    public void updateRole() {

        RoleEnum roleName = RoleEnum.ROLE_USER;
        Privilege privilege = createAuthentication.createPrivilege();
        Role role = createAuthentication.createRole();
        role.set_id(new ObjectId("60f44d6840846e186e5d7bf1"));
        Map<String, Object> toUpdateMap = Map.of("name", roleName, "privileges", Arrays.asList(privilege));

        when(roleRepository.findRoleByName(role.getName())).thenReturn(Optional.of(role));
        when(roleRepository.save(any())).thenReturn(any());
        when(roleRepository.findById(role.get_id().toString())).thenReturn(Optional.of(role));

        RoleDTO updatedRoleDTO = roleService.patchRole(roleName, toUpdateMap);

        Assertions.assertEquals(updatedRoleDTO.getName(), toUpdateMap.get("name"));
    }
}
