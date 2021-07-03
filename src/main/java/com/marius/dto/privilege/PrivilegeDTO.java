package com.marius.dto.privilege;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@NoArgsConstructor
@Getter
@Setter
public class PrivilegeDTO {

    private ObjectId _id;
    private String name;
}
