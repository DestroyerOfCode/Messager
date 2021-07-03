package com.marius.service.role;

import com.marius.businesslogic.role.RoleLogic;
import com.marius.dto.role.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleLogic roleLogic;

    @Autowired
    public RoleService(RoleLogic roleLogic) {
        this.roleLogic = roleLogic;
    }

    public RoleDTO createRole(RoleDTO dto) {
        return roleLogic.createRole(dto);
    }
}
