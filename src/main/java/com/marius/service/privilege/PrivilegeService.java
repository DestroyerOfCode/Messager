package com.marius.service.privilege;

import com.marius.businesslogic.privilege.PrivilegeLogic;
import com.marius.dto.privilege.PrivilegeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {

    private final PrivilegeLogic privilegeLogic;

    @Autowired
    public PrivilegeService(PrivilegeLogic privilegeLogic) {
        this.privilegeLogic = privilegeLogic;
    }

    public PrivilegeDTO createPrivilege(PrivilegeDTO dto) {
        return privilegeLogic.createPrivilege(dto);
    }
}
