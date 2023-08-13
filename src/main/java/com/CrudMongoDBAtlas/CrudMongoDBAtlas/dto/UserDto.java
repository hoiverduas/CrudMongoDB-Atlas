package com.CrudMongoDBAtlas.CrudMongoDBAtlas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class UserDto implements Serializable {

    final String  userName;
    final String  userLastName;
    final String  userEmail;
    final Integer userAge;
    final String userGender;

}
