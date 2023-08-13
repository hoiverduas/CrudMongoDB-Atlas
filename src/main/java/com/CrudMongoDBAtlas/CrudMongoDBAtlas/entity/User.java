package com.CrudMongoDBAtlas.CrudMongoDBAtlas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private Integer userId;
    private String  userName;
    private String  userLastName;
    private String  userEmail;
    private Integer userAge;
    private String userGender;
    private Integer userPassword;



}
