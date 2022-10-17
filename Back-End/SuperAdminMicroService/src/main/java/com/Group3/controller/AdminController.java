package com.Group3.controller;


import com.Group3.common.api.ApiResult;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdGp;
import com.Group3.entity.NdPatient;
import com.Group3.param.GPQueryParam;
import com.Group3.service.GpService;
import com.Group3.service.PatientService;
import com.Group3.service.impl.SuperAdminService;
import com.Group3.vo.GPVo;
import com.Group3.vo.PatientVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("Admin MicroService")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/admin")
public class AdminController {

    private final PatientService patientService;

    private final GpService gpService;

    @Autowired
    SuperAdminService  superAdminService;


    @ApiOperation("Get a GP's information")
    @GetMapping("/get/gp")
    @AuthCheck
    public ApiResult getGp(@RequestParam(name = "gid") Long gid) {
        if (!superAdminService.isAdmin())
            return ApiResult.error("Please log in as an Admin!");

        NdGp gp = gpService.getGp(gid);
        return ApiResult.ok(gp);
    }


    @AuthCheck
    @ApiOperation("Get all GP's information")
    @GetMapping("/get/gpList")
    public ApiResult listGp(GPQueryParam param) {
        if (!superAdminService.isAdmin())
            return ApiResult.error("Please log in as an Admin!");

        List<GPVo> list = gpService.listGp(param);
        return ApiResult.ok(list);
    }

    @ApiOperation("Get a Patient's information")
    @GetMapping("/get/patient")
    @AuthCheck
    public ApiResult getPatient(@RequestParam(name = "pid") Long pid) {
        if (!superAdminService.isAdmin())
            return ApiResult.error("Please log in as an Admin!");

        List<PatientVo> list = patientService.listPatient(pid);
        return ApiResult.ok(list);
    }

    @ApiOperation("Get all Patient's information")
    @GetMapping("/get/patientList")
    @AuthCheck
    public ApiResult getPatientList() {
        if (!superAdminService.isAdmin())
            return ApiResult.error("Please log in as an Admin!");

        List<NdPatient> list = patientService.list();
        return ApiResult.ok(list);
    }

}
