package com.marius.converter.privilege;

import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.model.domain.privilege.Privilege;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeConverter {

    public PrivilegeConverter() {}

    public Privilege dtoToEntity(PrivilegeDTO dto) {
        Privilege privilege = new Privilege();

        privilege.set_id(dto.get_id());
        privilege.setName(dto.getName());

        return privilege;
    }

    public PrivilegeDTO entityToDto(Privilege privilege) {
        PrivilegeDTO dto = new PrivilegeDTO();

        dto.set_id(privilege.get_id());
        dto.setName(privilege.getName());

        return dto;
    }
}
