package com.marius.model.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Document(value = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotNull(message = "The value id must be NOT null and hence must be set!")
    private ObjectId id;

    @NotBlank
    private String cityName;

    @NotBlank
    private String userName;

    @NotBlank
    private String userPassword;

    @NotBlank
    private String phoneNumber;

    private Boolean sendMessage;
}
