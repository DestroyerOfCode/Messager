package com.marius.model.domain.role;

import com.marius.model.domain.common.BaseEntity;
import com.marius.model.domain.privilege.Privilege;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Component
@Document(value = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {

    private String roleNumber;

    @NotBlank
    private RoleEnum name;

    @DBRef
    private Set<Privilege> privileges;
}
