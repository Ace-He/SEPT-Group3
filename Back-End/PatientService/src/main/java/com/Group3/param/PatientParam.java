package com.Group3.param;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientParam {
    private Long pid;
    private Long height;
    private Long weight;
    private String allergy;
    private String medicalHistory;
    private String status;

    public PatientParam(Long pid, Long height, Long weight, String allergy, String medicalHistory) {
        this.pid = pid;
        this.height = height;
        this.weight = weight;
        this.allergy = allergy;
        this.medicalHistory = medicalHistory;
    }

    public PatientParam(Long pid, String status) {
        this.pid = pid;
        this.status = status;
    }
}
