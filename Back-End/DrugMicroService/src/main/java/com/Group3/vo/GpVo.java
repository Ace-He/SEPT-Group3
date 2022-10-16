package com.Group3.vo;

import lombok.Data;

@Data
public class GpVo {

    private Long uid;
    private String userName;
    private String email;
    private String sex;
    private int age;
    private int userType;

    Long gid;
    int isFree;

}
