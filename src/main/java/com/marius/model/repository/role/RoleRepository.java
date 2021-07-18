package com.marius.model.repository.role;

import com.marius.model.domain.role.Role;
import com.marius.model.domain.role.RoleEnum;
import com.marius.model.repository.common.BaseRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role, String> {

    @Query("{'name' : ?0 }")
    Optional<Role> findRoleByName(RoleEnum name);

}
