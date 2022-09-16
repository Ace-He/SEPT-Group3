package com.Group3.param;

import lombok.Data;

@Data
public class UserRegisterParam {

    private String code;

    private String userName;
    private String email;
    private String sex;
    private int age;
    private String password;
    private int userType;

    private Object user;
}