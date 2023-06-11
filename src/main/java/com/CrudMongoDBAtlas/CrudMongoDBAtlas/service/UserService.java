package com.CrudMongoDBAtlas.CrudMongoDBAtlas.service;

import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public User aadUser(User user){

        return iUserRepository.save(user);
    }

    public List<User> findAllUser(){

        return iUserRepository.findAll();
    }

    public User getUserById(Integer userId ){

        return iUserRepository.findById(userId).get();
    }

    public List<User> filterUsersByProperty(String filter, Object value) {
        List<User> filteredUsers = new ArrayList<>();

        switch (filter.toLowerCase()) {
            case "username":
                filteredUsers = iUserRepository.findByUserName((String  ) value);
                break;
            case "userlastname":
                filteredUsers = iUserRepository.findByUserLastName((String) value);
                break;
            case "useremail":
                filteredUsers = iUserRepository.findByUserEmail((String) value);
                break;
            case "userage":
                filteredUsers = iUserRepository.findByUserAge((Integer) value);
                break;
            case "usergender":
                filteredUsers = iUserRepository.findByUserGender((String) value);
                break;
            case "userpassword":
                filteredUsers = iUserRepository.findByUserPassword((Integer) value);
                break;
            default:
                System.out.println("Propiedad no válida.");
                break;
        }

        return filteredUsers;
    }

  public User UpdateUser(User userRequest){

        User existingUser = iUserRepository.findById(userRequest.getUserId()).get();
             existingUser.setUserName(userRequest.getUserName());
             existingUser.setUserLastName(userRequest.getUserLastName());
             existingUser.setUserEmail(userRequest.getUserEmail());
             existingUser.setUserAge(userRequest.getUserAge());
             existingUser.setUserGender(userRequest.getUserGender());
             existingUser.setUserPassword(userRequest.getUserPassword());

             return iUserRepository.save(existingUser);
  }


  public Integer deleterUser(Integer userId){

        iUserRepository.deleteById(userId);

        return userId;
  }

}
