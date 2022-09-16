package com.Group3.param;

import lombok.Data;

@Data
public class PatientParam {
    private Long pid;
    private Long height;
    private Long weight;
    private String allergy;
    private String medicalHistory;
    private String status;
}
