package com.CrudMongoDBAtlas.CrudMongoDBAtlas.controller;


import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class Controller {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){

        return userService.aadUser(user);
    }

    @GetMapping
    public List<User> getUsers(){

        return userService.findAllUser();
    }

    @GetMapping("/{userId}")
    public User getUsersById(@PathVariable Integer userId){

        return userService.getUserById(userId);


    }

    @GetMapping("/filter/{filter}")
    public List<User> filterUsersByProperty(@PathVariable String filter, Object value){

        return userService.filterUsersByProperty(filter,value);
    }


    @PutMapping
    public User modifyUser(@RequestBody User user){
        return userService.UpdateUser(user);
    }

    @DeleteMapping("/{userId}")
    public Integer deleteUser(@PathVariable Integer userId){
        return userService.deleterUser(userId);
    }
}
