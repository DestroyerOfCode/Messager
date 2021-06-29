package com.marius.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String cityName;
    private String phoneNumber;
    private Boolean sendMessage;
}
