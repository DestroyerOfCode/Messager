package com.marius.dto.jwt;

import com.marius.model.domain.role.Role;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String jwt;
    private ObjectId id;
    private String userName;
    private String phoneNumber;
    private Set<Role> roles;
}
