package com.marius.businesslogic.privilege;

import com.marius.converter.privilege.PrivilegeConverter;
import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.model.domain.privilege.Privilege;
import com.marius.model.repository.privilege.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeLogic {

    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeConverter privilegeConverter;

    @Autowired
    public PrivilegeLogic(PrivilegeRepository privilegeRepository, PrivilegeConverter privilegeConverter) {
        this.privilegeRepository = privilegeRepository;
        this.privilegeConverter = privilegeConverter;
    }

    public PrivilegeDTO createPrivilege(PrivilegeDTO dto) {
        Privilege privilege = privilegeConverter.dtoToEntity(dto);
        privilege = privilegeRepository.insert(privilege);
        return privilegeConverter.entityToDto(privilege);
    }
}
