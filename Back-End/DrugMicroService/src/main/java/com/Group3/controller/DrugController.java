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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api("Drug module")
@RestController
@RequestMapping("/drug")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrugController {

    @Autowired
    DrugService drugService;



    /**
     * @param dids  Separate multiple drug ids with commas
     * @param param
     * @return
     */
    @AuthCheck
    @ApiOperation("A doctor prescribes drug for an appointment or modifies the drug")
    @PostMapping("/prescribe/{dids}")
    public ApiResult prescribe(@PathVariable(value = "dids") String dids, @RequestBody DrugParam param) {
//        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.<NdAppointment>lambdaQuery();
//        wrapper.eq(NdAppointment::getPid, param.getPid()).eq(NdAppointment::getGid, param.getGid());
//        List<NdAppointment> list = appointmentService.list(wrapper);
//        if (!(list.size() > 0)) {
//            return ApiResult.error().setMsg("This patient does not have an appointment and cannot be prescribed drug");
//        }
        if (!drugService.hasAppointment(param))
            return ApiResult.error("This patient does not have an appointment and cannot be prescribed drug");

//        NdPrescribe ndPrescribe = new NdPrescribe();
//        BeanUtil.copyProperties(param, ndPrescribe);
//        ndPrescribe.setDids(dids);
//        if (ObjectUtil.isNotEmpty(param.getId())) {
//            ndPrescribe.setId(param.getId());
//            prescribeService.updateById(ndPrescribe);
//            return ApiResult.ok(ndPrescribe).setMsg("Successful modification of drug");
//        }
        if(drugService.addNewPrescription(dids,param)){
            return ApiResult.ok("Prescribing success");
        }else{
            return ApiResult.ok("The drug was successfully modified");
        }
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
    @GetMapping("/getDrugList")
    public ApiResult getDrugList(DrugParam param) {

//        LambdaQueryWrapper<NdPrescribe> wrapper = Wrappers.<NdPrescribe>lambdaQuery();
//        wrapper.eq(NdPrescribe::getPid, param.getPid()).eq(NdPrescribe::getGid, param.getGid());
//        LambdaQueryWrapper<NdDrug> wrapper2 = Wrappers.<NdDrug>lambdaQuery();
//        List<NdPrescribe> list = prescribeService.list();
//        List<DrugVo> collect = list.stream().map(s -> {
//            DrugVo drugVo = new DrugVo();
//            BeanUtil.copyProperties(s, drugVo);
//            String[] split = s.getDids().split(",");
//            List<NdDrug> ndDrugList = new ArrayList<>();
//            for (String s1 : split) {
//                NdDrug ndDrug = drugService.getById(s1);
//                ndDrugList.add(ndDrug);
//            }
//            drugVo.setNdDrugList(ndDrugList);
//            return drugVo;
//        }).collect(Collectors.toList());
        List<DrugVo> drugList = drugService.checkDrugsByGp(param);
        return ApiResult.ok(drugList);
    }

    /**
     * @param id prescribe id
     * @return ApiResult
     */
    @AuthCheck
    @ApiOperation("Remove medications prescribed by doctors")
    @PostMapping("/remove/{id}")
    public ApiResult remove(@PathVariable Long id) {
        if (drugService.removePrescription(id))
            return ApiResult.ok("Successfully removed!");

        return ApiResult.error("Remove failure! This record doesn't exist.");

    }
}
