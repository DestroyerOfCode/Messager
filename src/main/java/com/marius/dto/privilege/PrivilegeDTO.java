package com.marius.dto.privilege;


import com.marius.dto.common.BaseDTO;
import com.marius.model.domain.privilege.PrivilegeEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PrivilegeDTO extends BaseDTO {

    private String privilegeNumber;
    private PrivilegeEnum name;
}
