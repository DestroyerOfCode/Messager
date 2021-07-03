package com.marius.dto.role;


import com.marius.dto.privilege.PrivilegeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private ObjectId _id;
    private String name;
    private Set<PrivilegeDTO> privileges;
}
