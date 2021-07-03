package com.marius.converter.role;

import com.marius.converter.privilege.PrivilegeConverter;
import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.dto.role.RoleDTO;
import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.role.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    private final PrivilegeConverter privilegeConverter;

    public RoleConverter(PrivilegeConverter privilegeConverter) {
        this.privilegeConverter = privilegeConverter;
    }

    public Role dtoToEntity(RoleDTO dto) {
        Role role = new Role();

        role.setName(dto.getName());
        role.set_id(dto.get_id());

        Set<Privilege> privileges = dto.getPrivileges().stream()
                .map(privilegeConverter::dtoToEntity)
                .collect(Collectors.toSet());

        role.setPrivileges(privileges);

        return role;
    }

    public RoleDTO entityToDto(Role role) {
        RoleDTO dto = new RoleDTO();

        dto.setName(role.getName());
        dto.set_id(role.get_id());
        Set<PrivilegeDTO> privileges = role.getPrivileges().stream()
                .map(privilegeConverter::entityToDto)
                .collect(Collectors.toSet());

        dto.setPrivileges(privileges);

        return dto;
    }
}