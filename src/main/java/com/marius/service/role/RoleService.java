package com.marius.service.role;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marius.businesslogic.role.RoleLogic;
import com.marius.dto.role.RoleDTO;
import com.marius.model.domain.role.Role;
import com.marius.model.domain.role.RoleEnum;
import com.marius.model.repository.role.RoleRepository;
import com.marius.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RoleService extends AbstractService<Role, RoleDTO> {

    private final RoleLogic roleLogic;
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleLogic roleLogic,ObjectMapper objectMapper, RoleRepository roleRepository) {
        super(objectMapper, roleRepository);
        this.roleLogic = roleLogic;
        this.objectMapper = objectMapper;
        this.roleRepository = roleRepository;
    }

    public RoleDTO createRole(RoleDTO dto) {
        return roleLogic.createRole(dto);
    }

    public RoleDTO patchRole(RoleEnum roleName, Map<String, Object> updatedValues) {
        Optional<Role> roleOptional = roleRepository.findRoleByName(roleName);

        if (roleOptional.isPresent())
            return super.patch(roleOptional.get().get_id().toString(), updatedValues);
        throw new RuntimeException("No user");

    }
}
