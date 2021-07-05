package com.marius.controller.user;

import com.marius.converter.user.UserToUserDtoConverter;
import com.marius.dto.jwt.JwtResponse;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.user.User;
import com.marius.service.user.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final UserToUserDtoConverter converter;


    @Autowired
    public UserController(UserService userService,
                          UserToUserDtoConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        if (null != users  && !users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        return  new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "userId") ObjectId userId) {
        Optional<User> userOptional = userService.getUser(userId);
        return userOptional.map(user -> new ResponseEntity<>(converter.entityToDto(user), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{userName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "userName") String userName) {
        Optional<User> userOptional = userService.getUserByUserName(userName);
        return userOptional.map(user -> new ResponseEntity<>(converter.entityToDto(user), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/auth/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        UserDTO createdUserDto = userService.createUser(dto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    /***
     * Endoint to call when logging in
     * @param credentials -> {
     *                    "userName": "xxx",
     *                    "password": "xxx"
     * }
     * @return logged in user & jwt
     */
    @PostMapping(value = "/auth/login")
    public ResponseEntity<JwtResponse> login(@RequestBody Map<String, String> credentials) {
        JwtResponse response = userService.login(credentials);
        return ResponseEntity.ok(response);
    }


}
