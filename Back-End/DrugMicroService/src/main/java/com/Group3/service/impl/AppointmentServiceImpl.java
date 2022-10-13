package com.Group3.service.impl;

import com.Group3.entity.NdAppointment;
import com.Group3.mapper.AppointmentMapper;
import com.Group3.service.AppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper,NdAppointment> implements AppointmentService {

}
