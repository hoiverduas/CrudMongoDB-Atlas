package com.CrudMongoDBAtlas.CrudMongoDBAtlas.repository;

import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User,Integer> {


    String findByUserEmail(String userEmail);
}
