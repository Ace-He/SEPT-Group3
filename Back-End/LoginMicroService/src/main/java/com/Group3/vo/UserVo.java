package com.Group3.vo;

import lombok.Data;

@Data
public class UserVo {
    private Long uid;
    private String userName;
    private String email;
    private String sex;
    private int age;
    private String userType;

    private Object user;
}
