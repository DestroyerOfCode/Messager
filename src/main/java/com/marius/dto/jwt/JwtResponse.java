package com.marius.dto.jwt;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

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
    private Boolean sendMessage;
}
