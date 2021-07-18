package com.marius.model.repository.common;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<Entity, ID> extends MongoRepository<Entity, ID> {

    @Query(value = "{ '_id' : ?0 }")
    Optional<Entity> findBy_id(ObjectId _id);

    @Query(value = "{ 'name' : ?0 }")
    Optional<Entity> findByName(String name);
}
