package com.marius.model.repository.role;

import com.marius.model.domain.role.Role;
import com.marius.model.domain.role.RoleEnum;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, ObjectId> {

    Optional<Role> findByName(RoleEnum name);

}
