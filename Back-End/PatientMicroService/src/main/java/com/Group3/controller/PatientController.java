package com.Group3.controller;


import cn.hutool.core.bean.BeanUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.bean.LocalUser;
import com.Group3.common.config.RabbitMQConfig;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdPatient;
import com.Group3.param.PatientParam;
import com.Group3.service.PatientService;
import com.Group3.service.RabbitMQService;
import com.Group3.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(value = "Patient MicroService")
@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC_Patient))
@Component
public class PatientController {

    private final PatientService patientService;

    @Resource
    private RabbitMQService rabbitMQService;



    /**
     * @param flag    0：add，1：modify
     * @param patient
     * @return ApiResult
     */
    @AuthCheck
    @ApiOperation("Add or modify your health information")
    @PostMapping("/edit/info/{flag}")
    public ApiResult editInfo(@PathVariable(value = "flag") Integer flag, @RequestBody PatientParam patient) {
//        NdPatient ndPatient = new NdPatient();
//        BeanUtil.copyProperties(patient, ndPatient);
//        if (flag == 1) {
//            if (patientService.updateById(ndPatient))
//                return ApiResult.ok("Edit health information successfully");
//            return ApiResult.error("Edit health information fail");
//        } else {
//            if (patientService.save(ndPatient))
//                return ApiResult.ok("Adding information successfully");
//            return ApiResult.error("Failed to add information");
//        }
        if (!patientService.editInformation(flag, patient))
            return ApiResult.error("Failed to add information, Please fill in the correct parameters. Hint: 0：add，1：modify ");

        return ApiResult.ok("Edit health information successfully");
    }

    /**
     *
     * @param patient  multiple status attributes in patient with commas (,)
     * @return ApiResult
     */
    @AuthCheck
    @ApiOperation("Modify patient status")
    @PostMapping("/edit/status")
    public ApiResult editStatus(@RequestBody PatientParam patient) {
        NdPatient ndPatient = new NdPatient();
        //patient.setPid(LocalUser.getPatient().getPid());
        BeanUtil.copyProperties(patient, ndPatient);
        if (patientService.updateById(ndPatient))
            return ApiResult.ok("Edit status successfully");
        return ApiResult.error("Edit status fail");
    }



    //The message sending interface for the patient
    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsg(msg);
    }


    //The message receiving interface for the patient
    @RabbitHandler
    public void process(Map map){
        System.out.println("Patient："+map.toString());
    }

}
