package com.Group3.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("nd_patient")
public class NdPatient {
    @TableId
    private Long pid;
    private Long uid;
    private Long height;
    private Long weight;
    private String allergy;
    private String medicalHistory;
    private String status;
}
