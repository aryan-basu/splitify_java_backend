package com.aryan.splitify.Repository;

import com.aryan.splitify.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);

    Optional<User> findById(ObjectId id);


}

