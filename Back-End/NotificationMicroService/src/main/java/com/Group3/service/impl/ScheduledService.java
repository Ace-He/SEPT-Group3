package com.Group3.service.impl;

import com.Group3.common.util.DateUtil;
import com.Group3.entity.NdAppointment;
import com.Group3.entity.NdNotification;
import com.Group3.service.AppointmentService;
import com.Group3.service.NotificationService;
import com.Group3.service.PrescribeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledService {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PrescribeService prescribeService;

    /**
     * Notification reminder at 10am every day
     */
    @Scheduled(cron = "0 0 10 * * ?")
    public void remindOfMedication() {
        System.out.println("Perform scheduled tasks at 10am every day to remind medication");
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        prescribeService.list().stream().forEach(s -> {
            NdNotification ndNews = new NdNotification(s.getPid(), "It's time to take the drug");
            ndNews.setPid(s.getPid());
            ndNews.setPrescribeId(s.getId());
            notificationService.save(ndNews);
        });
    }

    /**
     * Sending a Reservation Notification
     */
    @Scheduled(cron = "* 0/2 * * * ?")
    public void notifyAppointment() {
        System.out.println("The scheduled task was executed to send the scheduled notification. Procedure");
        List<NdAppointment> list1 = appointmentService.list();
        list1.stream().forEach(s -> {
            String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Date addDate = DateUtil.addTime(new Date(), 30);
            List<NdAppointment> list = appointmentService.list(Wrappers.<NdAppointment>query()
                    .lt("start_time", addDate)
                    .gt("start_time", addDate));

            for (NdAppointment ndAppointment : list) {
                NdNotification ndNotification = new NdNotification(ndAppointment.getPid(), "Have 30 minutes before the appointment time, so please get ready in advance");
                ndNotification.setPid(ndAppointment.getPid());
                ndNotification.setGid(ndAppointment.getGid());
                notificationService.save(ndNotification);
            }
            List<NdAppointment> list2 = appointmentService.list(Wrappers.<NdAppointment>query()
                    .lt("end_time", new Date()));
            for (NdAppointment ndAppointment : list2) {
                NdNotification ndNotification = new NdNotification();
                ndNotification.setContent("The appointment is closed");
                ndNotification.setPid(ndAppointment.getPid());
                ndNotification.setGid(ndAppointment.getGid());
                notificationService.save(ndNotification);
                appointmentService.removeById(ndAppointment.getAid());
            }
        });
    }
}