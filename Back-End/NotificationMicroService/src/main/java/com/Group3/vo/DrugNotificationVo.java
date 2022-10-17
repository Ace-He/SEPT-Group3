package com.Group3.vo;

import lombok.Data;

@Data
public class DrugNotificationVo {

    private Long nid;
    private Long pid;
    private String content;

    private DrugVo drugVo;

}
