package com.Group3.vo;

import lombok.Data;

@Data
public class PatientVo {

    private Long uid;
    private String userName;
    private String email;
    private String sex;
    private int age;
    private int userType;

    private Long pid;
    private Long height;
    private Long weight;
    private String allergy;
    private String medicalHistory;
    private String status;
}
