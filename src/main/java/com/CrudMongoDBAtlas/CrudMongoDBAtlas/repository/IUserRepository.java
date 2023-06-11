package com.CrudMongoDBAtlas.CrudMongoDBAtlas.repository;

import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IUserRepository extends MongoRepository<User,Integer> {

    @Query("{ userPassword: ?0 }")
    List<User> findByUserPassword(Integer userPassword);

    List<User> findByUserGender(String userGender);

    List<User> findByUserAge(Integer userAge);

    List<User> findByUserName(String userName);

    List<User> findByUserLastName(String userLastName);

    List<User> findByUserEmail(String userEmail);

}
