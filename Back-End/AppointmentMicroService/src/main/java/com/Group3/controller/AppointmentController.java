package com.Group3.controller;


import cn.hutool.core.bean.BeanUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.util.DateUtil;
import com.Group3.entity.NdAppointment;
import com.Group3.param.AppointmentParam;
import com.Group3.service.AppointmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@Api("Booking module")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;


    @ApiOperation("Make an appointment with a GP")
    @PostMapping("/add")
    public ApiResult add(@RequestBody AppointmentParam param) {
        if (param.getEndTime().getTime() < param.getStartTime().getTime()) {
            return ApiResult.error("Appointment end time should be greater than the start time");
        }
        Date date = DateUtil.beforeOneHourToNowDate(param.getStartTime());
        long time = date.getTime();
        if (time > new Date().getTime()) {
            return ApiResult.error("Appointment is required one hour in advance");
        }

        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(NdAppointment::getPid, param.getPid()).eq(NdAppointment::getGid, param.getGid()).eq(NdAppointment::getObsolete, 0);
        List<NdAppointment> list = appointmentService.list(wrapper);
        if (list.size() > 0) {
            return ApiResult.error("Appointment made, if you need to change your Appointment, please cancel the previous Appointment");
        }
        NdAppointment ndAppointment = new NdAppointment();
        BeanUtil.copyProperties(param, ndAppointment);
        appointmentService.save(ndAppointment);
        return ApiResult.ok("Appointment Success");
    }


    @ApiOperation("Cancel Appointment")
    @PostMapping("/del")
    public ApiResult del(@RequestBody AppointmentParam param) {
        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.<NdAppointment>lambdaQuery();
        wrapper.eq(NdAppointment::getGid, param.getGid()).eq(NdAppointment::getPid, param.getPid());
        if (!appointmentService.remove(wrapper)) {
            return ApiResult.error().setMsg("Failed to cancel reservation");
        }
        return ApiResult.ok().setMsg("Appointment cancellation success");
    }


}
