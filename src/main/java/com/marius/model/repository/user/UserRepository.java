package com.marius.model.repository.user;

import com.marius.model.domain.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findById(ObjectId userId);
    Optional<User> getUserByUserName(String userName);
}
