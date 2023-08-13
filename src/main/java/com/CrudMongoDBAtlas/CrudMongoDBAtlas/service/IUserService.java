package com.CrudMongoDBAtlas.CrudMongoDBAtlas.service;

import com.CrudMongoDBAtlas.CrudMongoDBAtlas.dto.UserDto;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;

import java.util.List;

public interface IUserService {

    public User addUser(User user);
    public List<UserDto> findAllUser();
    public UserDto getUserById(Integer userId);
    public List<UserDto> filterUsersByCriteria(String userName, String userLastName, String userEmail, Integer userAge, String userGender);
    public User UpdateUser(User userRequest);
    public Integer deleterUser(Integer userId);

}
