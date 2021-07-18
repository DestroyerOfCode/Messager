package com.marius.service.privilege;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marius.CreateAuthentication;
import com.marius.businesslogic.privilege.PrivilegeLogic;
import com.marius.converter.privilege.PrivilegeConverter;
import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.privilege.PrivilegeEnum;
import com.marius.model.repository.common.BaseRepository;
import com.marius.model.repository.privilege.PrivilegeRepository;
import com.marius.service.common.AbstractService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PrivilegeServiceTest {

    private AbstractService<Privilege, PrivilegeDTO> abstractService;

    private  ObjectMapper mapper;
    private static BaseRepository<Privilege, String> baseRepository;
    private static PrivilegeLogic privilegeLogic;
    private static PrivilegeRepository privilegeRepository;
    private static PrivilegeConverter privilegeConverter;

    private CreateAuthentication createAuthentication;
    @BeforeAll
    public static void setUpClass() {
        baseRepository = mock(BaseRepository.class);
        privilegeLogic = mock(PrivilegeLogic.class);
        privilegeRepository = mock(PrivilegeRepository.class);
        privilegeConverter = mock(PrivilegeConverter.class);
    }

    @BeforeEach
    public void setUp() {
        mapper = spy(ObjectMapper.class);
        this.abstractService = new PrivilegeService(mapper, privilegeLogic, privilegeRepository, privilegeConverter);
        this.createAuthentication = new CreateAuthentication();
    }

    @Test
    public void patchPrivilege(){

        Optional<Privilege> privilegeOptional = Optional.of(createAuthentication.createPrivilege());
        Map<String, Object> patchValues = new HashMap<>(){{put("name", PrivilegeEnum.ADD_ROLE_PRIVILEGE);}};

        when(privilegeRepository.findById(any())).thenReturn(privilegeOptional);
        when(privilegeRepository.save(any())).thenReturn(any());

        PrivilegeDTO updatedPrivilegeDTO = abstractService.patch("ADD_PRIVILEGE_PRIVILEGE", patchValues);

        Assertions.assertEquals(updatedPrivilegeDTO.getName(), PrivilegeEnum.ADD_ROLE_PRIVILEGE);
    }
}
