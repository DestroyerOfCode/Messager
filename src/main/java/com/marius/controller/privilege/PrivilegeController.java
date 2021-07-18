package com.marius.controller.privilege;

import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.model.domain.privilege.PrivilegeEnum;
import com.marius.service.privilege.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/privilege")
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    @Autowired
    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrivilegeDTO> createPrivilege(@Valid @RequestBody PrivilegeDTO dto) {
        PrivilegeDTO createdDTO = privilegeService.createPrivilege(dto);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping(value = "/{name}",
                  produces = MediaType.APPLICATION_JSON_VALUE,
                  consumes = MediaType.APPLICATION_JSON_VALUE
              )
    public ResponseEntity<PrivilegeDTO> patchPrivilege(@PathVariable("name") String id,
                                                    @RequestBody Map<String, Object> patchedMembers) {
        PrivilegeDTO patchedPrivilegeDTO = privilegeService.patch(id, patchedMembers);
        return new ResponseEntity<>(patchedPrivilegeDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<PrivilegeDTO> getPrivilege(@PathVariable("name") PrivilegeEnum name) {
        PrivilegeDTO createdDTO = privilegeService.getPrivilege(name);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<PrivilegeDTO> deletePrivilege(@PathVariable("name") PrivilegeEnum name) {
        PrivilegeDTO createdDTO = privilegeService.deletePrivilege(name);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }
}
