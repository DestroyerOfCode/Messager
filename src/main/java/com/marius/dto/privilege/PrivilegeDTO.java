package com.marius.dto.privilege;


import com.marius.model.domain.privilege.PrivilegeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@NoArgsConstructor
@Getter
@Setter
public class PrivilegeDTO {

    private ObjectId _id;
    private PrivilegeEnum name;
}
