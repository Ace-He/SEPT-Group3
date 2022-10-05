package com.Group3.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.entity.NdAppointment;
import com.Group3.entity.NdDrug;
import com.Group3.entity.NdPrescribe;
import com.Group3.param.DrugParam;
import com.Group3.service.*;
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

@Api("药物模块")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrugController {

    private final PrescribeService prescribeService;
    private final DrugService drugService;
    private final AppointmentService appointmentService;


    /**
     * @param dids  药物id多个用逗号隔开
     * @param param
     * @return
     */
    @ApiOperation("医生给预约过的患者开药,或者修改开药物")
    @PostMapping("/prescribe/{dids}")
    public ApiResult prescribe(@PathVariable(value = "dids") String dids, @RequestBody DrugParam param) {
        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.<NdAppointment>lambdaQuery();
        wrapper.eq(NdAppointment::getPid, param.getPid()).eq(NdAppointment::getGid, param.getGid());
        List<NdAppointment> list = appointmentService.list(wrapper);
        if (!(list.size() > 0)) {
            return ApiResult.error().setMsg("此患者没有预约你，无法开药");
        }
        NdPrescribe ndPrescribe = new NdPrescribe();
        BeanUtil.copyProperties(param, ndPrescribe);
        ndPrescribe.setDids(dids);
        if (ObjectUtil.isNotEmpty(param.getId())) {
            ndPrescribe.setId(param.getId());
            prescribeService.updateById(ndPrescribe);
            return ApiResult.ok().setMsg("修改药物成功");
        }
        prescribeService.save(ndPrescribe);
        return ApiResult.ok().setMsg("开药成功");
    }

    @ApiOperation("获取全部药物")
    @GetMapping("/list")
    public ApiResult drugList() {
        List<NdDrug> list = drugService.list();
        return ApiResult.ok(list);
    }

    @ApiOperation("患者查看给医生开的药，或者医生查看给患者开的药")
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
     * @return
     */
    @ApiOperation("删除医生给患者开的药")
    @PostMapping("/del/{id}")
    public ApiResult del(@PathVariable Long id) {
        prescribeService.removeById(id);
        return ApiResult.ok();
    }
}
