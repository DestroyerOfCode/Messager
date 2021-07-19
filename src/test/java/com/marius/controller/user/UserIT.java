package com.marius.controller.user;

import com.marius.CreateAuthentication;
import com.marius.dto.user.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIT {

    @Autowired
    private UserController userController;

    private CreateAuthentication createAuthentication;
    private UserDTO createdUserDTO;

    @BeforeEach
    public void setUp() {
        this.createAuthentication = new CreateAuthentication();
    }

    @Test
    @Description("Test CRUD operations on user by Creating a new user, getting him, patching, deleting and getting")
    public void createAndGetAndDeleteUser() {
        ResponseEntity<UserDTO> RRUserDTO = userController.createUser(createAuthentication.createUserDTO());
        if (HttpStatus.CREATED == RRUserDTO.getStatusCode())
            createdUserDTO = RRUserDTO.getBody();
        else
            Assertions.fail();

        getUser();
        patchUser();
        deleteUser();
        getUserAfterDelete();
    }

    public void getUser() {
        ResponseEntity<UserDTO> RRUserDTO = userController.getUserByName(createdUserDTO.getUserName());
        if (HttpStatus.OK == RRUserDTO.getStatusCode())
            Assertions.assertNotNull(RRUserDTO.getBody());
        else
            Assertions.fail();
    }
    public void getUserAfterDelete() {
        ResponseEntity<UserDTO> RRUserDTO = userController.getUserByName(createdUserDTO.getUserName());
        if (HttpStatus.NOT_FOUND == RRUserDTO.getStatusCode())
            Assertions.assertNull(RRUserDTO.getBody());
        else
            Assertions.fail();
    }

    private void patchUser() {
        Map<String, Object> patchValues = Map.of("userName", "Gerald");
        ResponseEntity<UserDTO> RRUserDTO = userController.patchUser(patchValues, createdUserDTO.getUserName());

        if (HttpStatus.CREATED == RRUserDTO.getStatusCode()) {
            Assertions.assertNotNull(RRUserDTO.getBody());
            createdUserDTO = RRUserDTO.getBody();
            Assertions.assertEquals(createdUserDTO.getUserName(), "Gerald");
        }
        else
            Assertions.fail();
    }

    public void deleteUser() {
        ResponseEntity<UserDTO> RRUserDTO = userController.deleteUser(createdUserDTO.getUserName());
        if (HttpStatus.OK == RRUserDTO.getStatusCode())
            Assertions.assertNotNull(RRUserDTO.getBody());
        else
            Assertions.fail();
    }
}
