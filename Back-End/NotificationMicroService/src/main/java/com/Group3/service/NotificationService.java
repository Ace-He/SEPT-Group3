package com.Group3.service;

import com.Group3.entity.NdNotification;
import com.Group3.vo.AppointmentNotificationVo;
import com.Group3.vo.DrugNotificationVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface NotificationService extends IService<NdNotification> {


    List<AppointmentNotificationVo> appointmentNotifis(Long pid);

    List<DrugNotificationVo> DrugNotifis(Long pid);
}
