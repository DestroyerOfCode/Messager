package com.marius.dto.user;

import com.marius.dto.role.RoleDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private ObjectId _id;
    private String cityName;
    private String phoneNumber;
    private String userPassword;
    private String userName;
    private Boolean sendMessage;
    private Set<RoleDTO> roles;
}
