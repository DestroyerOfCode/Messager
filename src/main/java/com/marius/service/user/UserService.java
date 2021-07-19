package com.marius.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marius.businesslogic.user.UserLogic;
import com.marius.dto.jwt.JwtResponse;
import com.marius.dto.user.UserDTO;
import com.marius.model.domain.user.User;
import com.marius.model.repository.user.UserRepository;
import com.marius.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService extends AbstractService<User, UserDTO> {

    private final UserLogic userLogic;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserLogic userLogic, ObjectMapper objectMapper, UserRepository userRepository) {
        super(objectMapper, userRepository);
        this.userLogic = userLogic;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        return userLogic.getUsers();
    }

    public Optional<User> getUser(String userId) {
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

    public UserDTO patchUser(String username, Map<String, Object> patchValues) {
        Optional<User> userToUpdate = userRepository.getUserByUserName(username);
        UserDTO updatedUser;
        if (userToUpdate.isPresent()) {
            updatedUser = super.patch(userToUpdate.get().get_id().toString(), patchValues);
            return updatedUser;
        }

        throw new RuntimeException("User does Not exist");
    }

    public UserDTO deleteUser(String username) {
        Optional<User> userToDelete = userRepository.getUserByUserName(username);
        if (userToDelete.isPresent()) {
            UserDTO deletedUserDTO = super.delete(userToDelete.get().get_id().toString());
            return deletedUserDTO;
        }
        throw new RuntimeException("User does Not exist");

    }
}
