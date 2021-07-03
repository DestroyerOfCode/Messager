package com.marius.model.repository.privilege;

import com.marius.model.domain.privilege.Privilege;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, ObjectId> {
}
