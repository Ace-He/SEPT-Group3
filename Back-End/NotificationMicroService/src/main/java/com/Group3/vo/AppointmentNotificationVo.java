package com.Group3.vo;


import com.Group3.entity.NdGp;
import lombok.Data;

@Data
public class AppointmentNotificationVo {

    private Long nid;
    private Long pid;
    private String content;

    private NdGp ndGp;

}
