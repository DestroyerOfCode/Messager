package com.marius.dto.user;

import com.marius.dto.common.BaseDTO;
import com.marius.dto.role.RoleDTO;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO extends BaseDTO {

    private String userNumber;
    private String cityName;
    private String phoneNumber;
    private String userPassword;
    private String userName;
    private Boolean sendMessage;
    private Set<RoleDTO> roles;
}
