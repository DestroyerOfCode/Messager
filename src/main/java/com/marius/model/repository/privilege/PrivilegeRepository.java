package com.marius.model.repository.privilege;

import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.privilege.PrivilegeEnum;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, ObjectId> {

    Optional<Privilege> findPrivilegeByName(PrivilegeEnum name);
}
