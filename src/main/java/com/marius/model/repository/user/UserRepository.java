package com.marius.model.repository.user;

import com.marius.model.domain.user.User;
import com.marius.model.repository.common.BaseRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

    @Query("{'userNumber': ?0 }")
    Optional<User> findUserByUserNumber(String userNumber);

    @Query("{'userName': ?0 }")
    Optional<User> getUserByUserName(String userName);
}
