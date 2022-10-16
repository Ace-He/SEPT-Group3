package com.Group3.service;

import com.Group3.entity.NdAppointment;
import com.Group3.param.AppointmentParam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AppointmentService extends IService<NdAppointment> {
    public boolean isValid(AppointmentParam param);

    public boolean addAppointment(AppointmentParam param);

    public boolean cancelAppointment(AppointmentParam param);
}
