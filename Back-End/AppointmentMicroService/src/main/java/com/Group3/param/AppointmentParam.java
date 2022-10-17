package com.Group3.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class AppointmentParam {

    @NotEmpty(message = "Patient ID can't be empty!")
    private Long pid;
    @NotEmpty(message = "GP ID can't be empty!")
    private Long gid;
    @NotEmpty(message = "Start time can't be empty!")
    private Date startTime;
    @NotEmpty(message = "End time can't be empty!")
    private Date endTime;
}
