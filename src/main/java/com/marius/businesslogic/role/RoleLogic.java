package com.marius.businesslogic.role;

import com.marius.converter.role.RoleConverter;
import com.marius.dto.role.RoleDTO;
import com.marius.model.domain.role.Role;
import com.marius.model.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleLogic {

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleLogic(RoleConverter roleConverter, RoleRepository roleRepository) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
    }

    public RoleDTO createRole(RoleDTO dto) {
        Role role = roleConverter.dtoToEntity(dto);
        role = roleRepository.insert(role);
        return roleConverter.entityToDto(role);
    }
}
