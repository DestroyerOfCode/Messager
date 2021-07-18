package com.marius.model.domain.privilege;

import com.marius.model.domain.common.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Document(value = "privilege")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Privilege extends BaseEntity {

    private String privilegeNumber;

    @NotBlank
    private PrivilegeEnum name;

}
