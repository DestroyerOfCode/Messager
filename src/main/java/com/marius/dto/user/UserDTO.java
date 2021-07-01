package com.marius.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private ObjectId id;
    private String cityName;
    private String phoneNumber;
    private String userPassword;
    private String userName;
    private Boolean sendMessage;
}
