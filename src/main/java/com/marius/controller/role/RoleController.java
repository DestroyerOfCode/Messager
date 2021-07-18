package com.marius.controller.role;

import com.marius.dto.role.RoleDTO;
import com.marius.model.domain.role.RoleEnum;
import com.marius.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO dto) {
        RoleDTO createdRole = roleService.createRole(dto);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{roleName}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("roleName") RoleEnum roleName,
                                              @RequestBody Map<String, Object> updatedValues) {
        RoleDTO updatedRole = roleService.patchRole(roleName, updatedValues);
        return new ResponseEntity<>(updatedRole, HttpStatus.CREATED);
    }
}
