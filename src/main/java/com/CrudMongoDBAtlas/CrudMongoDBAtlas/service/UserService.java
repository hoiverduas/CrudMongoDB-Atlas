package com.CrudMongoDBAtlas.CrudMongoDBAtlas.service;

import com.CrudMongoDBAtlas.CrudMongoDBAtlas.dto.UserDto;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity.User;
import com.CrudMongoDBAtlas.CrudMongoDBAtlas.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){

        return userRepository.save(user);
    }

    public List<UserDto> findAllUser(){

        List<User> userList = userRepository.findAll();
        List<UserDto> userDTOList = userList.stream().map(user -> {
            UserDto userDto = new UserDto(user.getUserName(), user.getUserLastName(), user.getUserEmail(), user.getUserAge(), user.getUserGender());
            return userDto;
        })
                .collect(Collectors.toList());

        return userDTOList;
    }

    public UserDto getUserById(Integer userId){

        User user = userRepository.findById(userId).get();
        UserDto userDto = new UserDto(user.getUserName(),user.getUserLastName(),user.getUserEmail(),user.getUserAge(), user.getUserGender());

        return userDto;
    }

    public List<UserDto> filterUsersByCriteria(String userName, String userLastName, String userEmail, Integer userAge, String userGender) {
        return userRepository.findAll().stream()
                .filter(user -> userName == null || user.getUserName().equals(userName))
                .filter(user -> userLastName == null || user.getUserLastName().equals(userLastName))
                .filter(user -> userEmail == null || user.getUserEmail().equals(userEmail))
                .filter(user -> userAge == null || user.getUserAge().equals(userAge))
                .filter(user -> userGender == null || user.getUserGender().equals(userGender))
                .map(user -> new UserDto(user.getUserName(), user.getUserLastName(), user.getUserEmail(), user.getUserAge(), user.getUserGender()))
                .collect(Collectors.toList());
    }

  public User UpdateUser(User userRequest){

        User existingUser = userRepository.findById(userRequest.getUserId()).get();
             existingUser.setUserName(userRequest.getUserName());
             existingUser.setUserLastName(userRequest.getUserLastName());
             existingUser.setUserEmail(userRequest.getUserEmail());
             existingUser.setUserAge(userRequest.getUserAge());
             existingUser.setUserGender(userRequest.getUserGender());
             existingUser.setUserPassword(userRequest.getUserPassword());

             return userRepository.save(existingUser);
  }


  public Integer deleterUser(Integer userId){

      userRepository.deleteById(userId);

        return userId;
  }

}
