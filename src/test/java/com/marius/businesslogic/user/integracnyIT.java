package com.marius.businesslogic.user;

import com.marius.controller.privilege.PrivilegeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class integracnyIT {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    PrivilegeController privilegeController;

    @Test
    public void testing() {
        int x =2;
//        PrivilegeDTO privilegeDTO = privilegeController.createPrivilege(new PrivilegeDTO()).getBody();
//        Assertions.assertNotNull(privilegeDTO);
        System.out.println("testing");
    }
    @Test
    public void testing1() {
        int x =2;
//        PrivilegeDTO privilegeDTO = privilegeController.createPrivilege(new PrivilegeDTO()).getBody();
//        Assertions.assertNotNull(privilegeDTO);
        System.out.println("testing1");
    }
    @Test
    public void testing2() {
        int x =2;
//        PrivilegeDTO privilegeDTO = privilegeController.createPrivilege(new PrivilegeDTO()).getBody();
//        Assertions.assertNotNull(privilegeDTO);
        System.out.println("testing2");
    }
}
