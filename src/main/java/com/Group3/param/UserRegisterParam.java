package com.Group3.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterParam {

    private String code;

    private String userName;
    private String email;
    private String sex;
    private int age;
    private String password;
    private int userType;
    private Object user;
    

    public UserRegisterParam(String code, String userName, String email, String sex, int age, String password, int userType) {
        this.code = code;
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.age = age;
        this.password = password;
        this.userType = userType;
    }
}