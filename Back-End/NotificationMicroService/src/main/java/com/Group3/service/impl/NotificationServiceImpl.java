package com.Group3.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.Group3.entity.NdDrug;
import com.Group3.entity.NdGp;
import com.Group3.entity.NdNotification;
import com.Group3.entity.NdPrescribe;
import com.Group3.mapper.NotificationMapper;
import com.Group3.service.DrugService;
import com.Group3.service.GPService;
import com.Group3.service.NotificationService;
import com.Group3.service.PrescribeService;
import com.Group3.vo.DrugVo;
import com.Group3.vo.AppointmentNotificationVo;
import com.Group3.vo.DrugNotificationVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, NdNotification> implements NotificationService {
    @Autowired
    PrescribeService prescribeService;
    @Autowired
    GPService gpService;
    @Autowired
    DrugService drugService;


    @Override
    public List<AppointmentNotificationVo> appointmentNotifis(Long pid) {
        LambdaQueryWrapper<NdNotification> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(NdNotification::getPid, pid).isNotNull(NdNotification::getGid);
        List<NdNotification> list = this.list(wrapper);
        List<AppointmentNotificationVo> collect = list.stream().map(s -> {
            AppointmentNotificationVo appointmentNotificationVo = new AppointmentNotificationVo();
            BeanUtil.copyProperties(s, appointmentNotificationVo);
            NdGp ndGp = gpService.getById(s.getGid());
            appointmentNotificationVo.setNdGp(ndGp);
            return appointmentNotificationVo;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<DrugNotificationVo> DrugNotifis(Long pid) {
        LambdaQueryWrapper<NdNotification> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(NdNotification::getPid, pid).isNotNull(NdNotification::getPrescribeId);
        List<NdNotification> list = this.list(wrapper);
        List<DrugNotificationVo> collect = list.stream().map(s -> {
            DrugNotificationVo drugNotificationVo = new DrugNotificationVo();
            BeanUtil.copyProperties(s, drugNotificationVo);
            NdPrescribe ndPrescribe = prescribeService.getById(s.getPrescribeId());
            String[] split = ndPrescribe.getDids().split(",");
            DrugVo drugVo = new DrugVo();
            BeanUtil.copyProperties(ndPrescribe, drugVo);
            for (String s1 : split) {
                NdDrug ndDrug = drugService.getById(s1);
                drugVo.getNdDrugList().add(ndDrug);
            }
            return drugNotificationVo;
        }).collect(Collectors.toList());

        return collect;
    }


}
