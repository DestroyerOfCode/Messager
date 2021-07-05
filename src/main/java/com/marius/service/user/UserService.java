package com.marius.service.user;

import com.marius.businesslogic.user.UserLogic;
import com.marius.dto.jwt.JwtResponse;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.user.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserLogic userLogic;

    @Autowired
    public UserService(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    public List<UserDTO> getUsers() {
        return userLogic.getUsers();
    }

    public Optional<User> getUser(ObjectId userId) {
        return userLogic.getUser(userId);
    }

    public Optional<User> getUserByUserName(String userName) {
        return userLogic.getUserByUserName(userName);
    }

    public UserDTO createUser(UserDTO dto) {
        return userLogic.createUser(dto);
    }

    public JwtResponse login(Map<String, String> credentials) {
        return userLogic.login(credentials);
    }
}
