package com.Group3.controller;


import com.Group3.common.api.ApiResult;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.param.AppointmentParam;
import com.Group3.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api("Booking module")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;


    @ApiOperation("Make an appointment with a GP")
    @PostMapping("/add")
    @AuthCheck
    public ApiResult add(@RequestBody AppointmentParam param) {
        if (!appointmentService.isValid(param))
            return ApiResult.error("Please make the appointment with a valid time");

        if(!appointmentService.addAppointment(param))
            return ApiResult.error("Appointment exist, if you need to change your Appointment, please cancel the previous Appointment");

        return ApiResult.ok("Add appointment success");
    }


    @ApiOperation("Cancel Appointment")
    @PostMapping("/cancel")
    @AuthCheck
    public ApiResult cancel(@RequestBody AppointmentParam param) {
        if (!appointmentService.cancelAppointment(param))
            return ApiResult.error("This appointment doesn't exist!");

        return ApiResult.ok("Appointment cancellation success!");
    }


}
