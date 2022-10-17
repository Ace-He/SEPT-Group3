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
     * Get Appointment Notification
     *
     * @param pid
     * @return ApiResult
     */
    @AuthCheck
    @GetMapping("/getAppointment/{pid}")
    public ApiResult byIdPid(@PathVariable("pid") Long pid) {
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
        List<DrugNotificationVo> list = notificationService.DrugNotifis(pid);
        if(ObjectUtil.isEmpty(list))
            return ApiResult.ok("No drug notifications");
        else
            return ApiResult.ok(list);
    }


    /**
     * Delete notification
     */
    @AuthCheck
    @PostMapping("/delById/{id}")
    public ApiResult delById(@PathVariable("id") Long id){
        notificationService.removeById(id);
        return ApiResult.ok("Delete successfully");
    }

}
