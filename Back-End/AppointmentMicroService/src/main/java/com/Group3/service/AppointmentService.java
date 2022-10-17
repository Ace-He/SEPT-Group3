package com.Group3.service;

import com.Group3.entity.NdAppointment;
import com.Group3.param.AppointmentParam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AppointmentService extends IService<NdAppointment> {
    boolean isValid(AppointmentParam param);

    boolean addAppointment(AppointmentParam param);

    boolean cancelAppointment(AppointmentParam param);
}
