package com.Group3.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdAppointment;
import com.Group3.entity.NdDrug;
import com.Group3.entity.NdPrescribe;
import com.Group3.param.DrugParam;
import com.Group3.service.AppointmentService;
import com.Group3.service.DrugService;
import com.Group3.service.PrescribeService;
import com.Group3.vo.DrugVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api("Drug module")
@RestController
@RequestMapping("/drug")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrugController {

    private final PrescribeService prescribeService;
    private final DrugService drugService;
    private final AppointmentService appointmentService;


    /**
     * @param dids  Separate multiple drug ids with commas
     * @param param
     * @return
     */
    @AuthCheck
    @ApiOperation("A doctor prescribes medication for an appointment or modifies the medication")
    @PostMapping("/prescribe/{dids}")
    public ApiResult prescribe(@PathVariable(value = "dids") String dids, @RequestBody DrugParam param) {
        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.<NdAppointment>lambdaQuery();
        wrapper.eq(NdAppointment::getPid, param.getPid()).eq(NdAppointment::getGid, param.getGid());
        List<NdAppointment> list = appointmentService.list(wrapper);
        if (!(list.size() > 0)) {
            return ApiResult.error().setMsg("This patient does not have an appointment and cannot be prescribed drug");
        }
        NdPrescribe ndPrescribe = new NdPrescribe();
        BeanUtil.copyProperties(param, ndPrescribe);
        ndPrescribe.setDids(dids);
        if (ObjectUtil.isNotEmpty(param.getId())) {
            ndPrescribe.setId(param.getId());
            prescribeService.updateById(ndPrescribe);
            return ApiResult.ok(ndPrescribe).setMsg("Successful modification of drug");
        }
        prescribeService.save(ndPrescribe);
        return ApiResult.ok(ndPrescribe
        ).setMsg("Prescribing success");
    }


    @AuthCheck
    @ApiOperation("Access to all medicines")
    @GetMapping("/list")
    public ApiResult drugList() {
        List<NdDrug> list = drugService.list();
        return ApiResult.ok(list);
    }
    @AuthCheck
    @ApiOperation("The patient checks the medication prescribed to the doctor, or the doctor checks the medication prescribed to the patient")
    @GetMapping("/getByPidOrGid")
    public ApiResult getByPidOrGid(DrugParam param) {

        LambdaQueryWrapper<NdPrescribe> wrapper = Wrappers.<NdPrescribe>lambdaQuery();
        wrapper.eq(NdPrescribe::getPid, param.getPid()).eq(NdPrescribe::getGid, param.getGid());
        LambdaQueryWrapper<NdDrug> wrapper2 = Wrappers.<NdDrug>lambdaQuery();
        List<NdPrescribe> list = prescribeService.list();
        List<DrugVo> collect = list.stream().map(s -> {
            DrugVo drugVo = new DrugVo();
            BeanUtil.copyProperties(s, drugVo);
            String[] split = s.getDids().split(",");
            List<NdDrug> ndDrugList = new ArrayList<>();
            for (String s1 : split) {
                NdDrug ndDrug = drugService.getById(s1);
                ndDrugList.add(ndDrug);
            }
            drugVo.setNdDrugList(ndDrugList);
            return drugVo;
        }).collect(Collectors.toList());
        return ApiResult.ok(collect);
    }

    /**
     * @param id prescribe id
     * @return ApiResult
     */
    @AuthCheck
    @ApiOperation("Remove medications prescribed by doctors")
    @PostMapping("/del/{id}")
    public ApiResult del(@PathVariable Long id) {
        prescribeService.removeById(id);
        return ApiResult.ok();
    }
}
