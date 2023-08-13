package com.CrudMongoDBAtlas.CrudMongoDBAtlas.controller;


import com.CrudMongoDBAtlas.CrudMongoDBAtlas.dto.UserDto;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){

        return userService.addUser(user);
    }

    @GetMapping
    public List<UserDto> getUsers(){

        return userService.findAllUser();
    }

    @GetMapping("/{userId}")
    public UserDto getUsersById(@PathVariable Integer userId){

        return userService.getUserById(userId);


    }


    @GetMapping("/filter")
    public ResponseEntity<List<UserDto>> filterUsersByCriteria(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String userLastName,
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) Integer userAge,
            @RequestParam(required = false) String userGender) {

        List<UserDto> filteredUsers = userService.filterUsersByCriteria(userName, userLastName, userEmail, userAge, userGender);

        return ResponseEntity.ok(filteredUsers);
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
