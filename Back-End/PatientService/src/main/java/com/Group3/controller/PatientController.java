package com.Group3.controller;


import cn.hutool.core.bean.BeanUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdPatient;
import com.Group3.param.PatientParam;
import com.Group3.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "患者模块")
@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final PatientService patientService;


    /**
     * @param flag    0：add，1：modify
     * @param patient
     * @return
     */
    @AuthCheck
    @ApiOperation("Add or modify your health information")
    @PostMapping("/edit/info/{flag}")
    public ApiResult editInfo(@PathVariable(value = "flag") Integer flag, @RequestBody PatientParam patient) {
        NdPatient ndPatient = new NdPatient();
        BeanUtil.copyProperties(patient, ndPatient);
        if (flag == 1) {
            if (patientService.updateById(ndPatient))
                return ApiResult.ok("Edit health information successfully");
            return ApiResult.error("Edit health information fail");
        } else {
            if (patientService.save(ndPatient))
                return ApiResult.ok("Adding information successfully");
            return ApiResult.error("Failed to add information");
        }
    }

    /**
     *
     * @param Separate multiple status attributes in patient with commas (,)
     * @return
     */
    @AuthCheck
    @ApiOperation("Modify patient status")
    @PostMapping("/edit/status")
    public ApiResult editStatus(@RequestBody PatientParam patient) {
        NdPatient ndPatient = new NdPatient();
        BeanUtil.copyProperties(patient, ndPatient);
        if (patientService.updateById(ndPatient))
            return ApiResult.ok("Edit status successfully");
        return ApiResult.error("Edit status fail");
    }


}
