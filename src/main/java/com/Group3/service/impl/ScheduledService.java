package com.Group3.service.impl;

import com.Group3.common.util.DateUtil;
import com.Group3.entity.NdAppointment;
import com.Group3.entity.NdNews;
import com.Group3.service.AppointmentService;
import com.Group3.service.NewsService;
import com.Group3.service.PrescribeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ScheduledService {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private PrescribeService prescribeService;

    /**
     * 每天中午12点提醒用药
     */
//    @Scheduled(cron = "0 0 12 * * ?")
    public void remindOfMedication() {
        System.out.println("执行定时任务 每天中午12点提醒用药");
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        prescribeService.list().stream().forEach(s -> {
            NdNews ndNews = new NdNews(s.getPid(), "大朗，该喝药了");
            ndNews.setPid(s.getPid());
            ndNews.setPrescribeId(s.getId());
            newsService.save(ndNews);
        });
    }

    /**
     * cron
     * 秒 分 时 日 月 周几~
     * 发送预约通知
     */
//    @Scheduled(cron = "* 0/2 * * * ?")
    public void notifyAppointment() {
        System.out.println("执行定时任务 发送预约通知");
        List<NdAppointment> list1 = appointmentService.list();
        list1.stream().forEach(s -> {
            String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Date addDate = DateUtil.addTime(new Date(), 30);
            List<NdAppointment> list = appointmentService.list(Wrappers.<NdAppointment>query()
                    .lt("start_time", addDate)
                    .gt("start_time", addDate));

            for (NdAppointment ndAppointment : list) {
                NdNews ndNews = new NdNews(ndAppointment.getPid(), "距离预约时间还有三十分钟，请提前做好准备");
                ndNews.setPid(ndAppointment.getPid());
                ndNews.setGid(ndAppointment.getGid());
                newsService.save(ndNews);
            }
            List<NdAppointment> list2 = appointmentService.list(Wrappers.<NdAppointment>query()
                    .lt("end_time", new Date()));
            for (NdAppointment ndAppointment : list2) {
                NdNews ndNews = new NdNews();
                ndNews.setContent("预约已结束");
                ndNews.setPid(ndAppointment.getPid());
                ndNews.setGid(ndAppointment.getGid());
                newsService.save(ndNews);
                appointmentService.removeById(ndAppointment.getAid());
            }
        });
    }
}