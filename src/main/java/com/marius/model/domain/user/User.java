package com.marius.model.domain.user;

import com.marius.model.domain.common.BaseEntity;
import com.marius.model.domain.role.Role;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Component
@Document(value = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    private String userNumber;

    private String cityName;

    @NotBlank
    private String userName;

    @NotBlank
    private String userPassword;

    private String phoneNumber;

    private Boolean sendMessage;

    @NotBlank
    @Email
    private String email;
    @DBRef
    private Set<Role> roles;

}

