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
    @ApiOperation("添加 或者 修改 自己的健康信息")
    @PostMapping("/edit/info/{flag}")
    public ApiResult editInfo(@PathVariable(value = "flag") Integer flag, @RequestBody PatientParam patient) {
        NdPatient ndPatient = new NdPatient();
        BeanUtil.copyProperties(patient, ndPatient);
        if (flag == 1) {
            if (patientService.updateById(ndPatient))
                return ApiResult.ok("修改信息成功(Edit health information successfully)");
            return ApiResult.error("修改信息失败(Edit health information fail)");
        } else {
            if (patientService.save(ndPatient))
                return ApiResult.ok("添加信息成功");
            return ApiResult.error("添加信息失败");
        }
    }

    /**
     *
     * @param patient 中status属性多个用逗号隔开
     * @return
     */
    @ApiOperation("添加 或者 修改患者状态")
    @PostMapping("/edit/status")
    public ApiResult editStatus(@RequestBody PatientParam patient) {
        NdPatient ndPatient = new NdPatient();
        BeanUtil.copyProperties(patient, ndPatient);
        if (patientService.updateById(ndPatient))
            return ApiResult.ok("修改患者状态成功(Edit status successfully)");
        return ApiResult.error("修改患者状态失败(Edit status fail)");
    }


}
