package com.Group3.controller;


import com.Group3.common.api.ApiResult;
import com.Group3.common.bean.LocalUser;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.param.GPQueryParam;
import com.Group3.service.GpService;
import com.Group3.service.PatientService;
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

    @AuthCheck
    @ApiOperation("Get all GP's information")
    @GetMapping("/gpList")
    public ApiResult listGp(GPQueryParam param) {
        if(LocalUser.getUser().getUserType() != 0)return ApiResult.error("Please log in as an Admin!");
        List<GPVo> list = gpService.listGp(param);
        return ApiResult.ok(list);
    }

    @ApiOperation("Get all Patient's information")
    @GetMapping("/patientList")
    @AuthCheck
    public ApiResult listPatient(@RequestParam(name = "pid") Long pid) {
        if(LocalUser.getUser().getUserType() != 0)return ApiResult.error().setMsg("Please log in as an Admin!");
        List<PatientVo> list = patientService.listPatient(pid);
        return ApiResult.ok(list);
    }

}
