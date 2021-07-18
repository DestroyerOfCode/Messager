package com.marius.model.repository.privilege;

import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.privilege.PrivilegeEnum;
import com.marius.model.repository.common.BaseRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends BaseRepository<Privilege, String> {

    @Query("{ 'name' : ?0 }")
    Optional<Privilege> findPrivilegeByName(PrivilegeEnum name);

    @Query(value = "{ 'name' : ?0 }", delete = true)
    Optional<Privilege> deletePrivilegeByName(PrivilegeEnum name);
}
