package com.Group3.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdDrug;
import com.Group3.entity.NdNotification;
import com.Group3.entity.NdPrescribe;
import com.Group3.service.DrugService;
import com.Group3.service.GPService;
import com.Group3.service.NotificationService;
import com.Group3.service.PrescribeService;
import com.Group3.vo.DrugVo;
import com.Group3.vo.AppointmentNotificationVo;
import com.Group3.vo.DrugNotificationVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("Notification MicroService")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;



    /**
     * Delete notification
     */
    @AuthCheck
    @PostMapping("/delById/{id}")
    public ApiResult delById(@PathVariable("id") Long id){
        notificationService.removeById(id);
        return ApiResult.ok("Delete successfully");
    }



    /**
     * Get Appointment Notification
     *
     * @param pid
     * @return
     */
    @AuthCheck
    @GetMapping("/getAppointment/{pid}")
    public ApiResult byIdPid(@PathVariable("pid") Long pid) {
//        LambdaQueryWrapper<NdNotification> wrapper = Wrappers.lambdaQuery();
//        wrapper.eq(NdNotification::getPid, pid).isNotNull(NdNotification::getGid);
//        List<NdNotification> list = notificationService.list(wrapper);
//        List<NotificationVo> collect = list.stream().map(s -> {
//            NotificationVo notificationVo = new NotificationVo();
//            BeanUtil.copyProperties(s, notificationVo);
//            NdGp ndGp = gpService.getById(s.getGid());
//            notificationVo.setNdGp(ndGp);
//            return notificationVo;
//        }).collect(Collectors.toList());
        List<AppointmentNotificationVo> list = notificationService.appointmentNotifis(pid);
        if(ObjectUtil.isEmpty(list))
            return ApiResult.ok("No appointment notifications");
        else
            return ApiResult.ok(list);
    }


    /**
     * Get drug Notification
     * @param pid
     * @return
     */
    @AuthCheck
    @GetMapping("/getDrug/{pid}")
    public ApiResult getDrug(@PathVariable("pid") Long pid) {
//        LambdaQueryWrapper<NdNotification> wrapper = Wrappers.lambdaQuery();
//        wrapper.eq(NdNotification::getPid, pid).isNotNull(NdNotification::getPrescribeId);
//        List<NdNotification> list = notificationService.list(wrapper);
//        List<DrugNotificationVo> collect = list.stream().map(s -> {
//            DrugNotificationVo drugNotificationVo = new DrugNotificationVo();
//            BeanUtil.copyProperties(s, drugNotificationVo);
//            NdPrescribe ndPrescribe = prescribeService.getById(s.getPrescribeId());
//            String[] split = ndPrescribe.getDids().split(",");
//            DrugVo drugVo = new DrugVo();
//            BeanUtil.copyProperties(ndPrescribe, drugVo);
//            for (String s1 : split) {
//                NdDrug ndDrug = drugService.getById(s1);
//                drugVo.getNdDrugList().add(ndDrug);
//            }
//            return drugNotificationVo;
//        }).collect(Collectors.toList());
        List<DrugNotificationVo> list = notificationService.DrugNotifis(pid);
        if(ObjectUtil.isEmpty(list))
            return ApiResult.ok("No drug notifications");
        else
            return ApiResult.ok(list);
    }

}
