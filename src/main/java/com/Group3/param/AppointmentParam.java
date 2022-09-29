package com.Group3.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class AppointmentParam {

    @NotEmpty(message = "患者id不能为空(Patient ID can't be empty!)")
    private Long pid;
    @NotEmpty(message = "预约医生id不能为空(GP ID can't be empty!)")
    private Long gid;
    @NotEmpty(message = "开始时间不能为空(Start time can't be empty!)")
    private Date startTime;
    @NotEmpty(message = "结束时间不能为空(End time can't be empty!)")
    private Date endTime;
}
