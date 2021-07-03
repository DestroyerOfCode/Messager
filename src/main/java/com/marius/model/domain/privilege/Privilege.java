package com.marius.model.domain.privilege;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Document(value = "privilege")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {

    @Id
    private ObjectId _id;

    @NotBlank
    private PrivilegeEnum name;

}
