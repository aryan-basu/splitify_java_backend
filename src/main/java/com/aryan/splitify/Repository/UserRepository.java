package com.aryan.splitify.Repository;

import com.aryan.splitify.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);

    Optional<User> findById(ObjectId id);


    List<User> findByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(String name, String email);


    List<User> findByIdInAndNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(List<ObjectId> ids, String name, String email);

}


