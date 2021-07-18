package com.marius.service.privilege;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marius.businesslogic.privilege.PrivilegeLogic;
import com.marius.converter.privilege.PrivilegeConverter;
import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.privilege.PrivilegeEnum;
import com.marius.model.repository.privilege.PrivilegeRepository;
import com.marius.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrivilegeService extends AbstractService<Privilege, PrivilegeDTO> {

    private final ObjectMapper objectMapper;
    private final PrivilegeLogic privilegeLogic;
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeConverter privilegeConverter;

    @Autowired
    public PrivilegeService(ObjectMapper objectMapper, PrivilegeLogic privilegeLogic, PrivilegeRepository privilegeRepository, PrivilegeConverter privilegeConverter) {
        super(objectMapper, privilegeRepository);
        this.objectMapper = objectMapper;
        this.privilegeLogic = privilegeLogic;
        this.privilegeRepository = privilegeRepository;
        this.privilegeConverter = privilegeConverter;
    }


    public PrivilegeDTO createPrivilege(PrivilegeDTO dto) {
        return privilegeLogic.createPrivilege(dto);
    }

    @Override
    public PrivilegeDTO patch(String name, Map<String, Object> dto) {
        PrivilegeDTO privilegeDTO = super.patch(name, dto);
        return  privilegeDTO;
    }

    public PrivilegeDTO getPrivilege(PrivilegeEnum name) {
        return privilegeLogic.getPrivilege(name);
    }

    public PrivilegeDTO deletePrivilege(PrivilegeEnum name) {
        return privilegeLogic.deletePrivilege(name);
    }
}
