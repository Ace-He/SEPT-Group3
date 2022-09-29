package com.Group3.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class NdAppointment {

    private Long aid;  //Appointment ID
    private Long pid;  //Patient ID
    private Long gid;  //GP ID

    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+10"
    )
    private Date startTime;

    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+10"
    )
    private Date endTime;
    private Long obsolete;
    @TableLogic
    private Long id_del;
}
