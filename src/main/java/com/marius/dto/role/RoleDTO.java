package com.marius.dto.role;


import com.marius.dto.common.BaseDTO;
import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.model.domain.role.RoleEnum;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleDTO extends BaseDTO {

    private String roleNumber;
    private RoleEnum name;
    private Set<PrivilegeDTO> privileges;
}
