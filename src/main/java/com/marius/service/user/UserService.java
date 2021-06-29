package com.marius.service.user;

import com.marius.businesslogic.user.UserLogic;
import com.marius.dto.UserDTO;
import com.marius.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public UserDTO createUser(UserDTO dto) {
        return userLogic.createUser(dto);
    }
}
