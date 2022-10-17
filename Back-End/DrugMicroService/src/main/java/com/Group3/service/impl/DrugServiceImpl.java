package com.Group3.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.entity.NdAppointment;
import com.Group3.entity.NdDrug;
import com.Group3.entity.NdPrescribe;
import com.Group3.mapper.DrugMapper;
import com.Group3.param.DrugParam;
import com.Group3.service.AppointmentService;
import com.Group3.service.DrugService;
import com.Group3.service.PrescribeService;
import com.Group3.vo.DrugVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrugServiceImpl extends ServiceImpl<DrugMapper, NdDrug> implements DrugService {

    @Autowired
    PrescribeService prescribeService;
    @Autowired
    AppointmentService appointmentService;


    @Override
    public boolean hasAppointment(DrugParam param) {
        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.<NdAppointment>lambdaQuery();
        wrapper.eq(NdAppointment::getPid, param.getPid()).eq(NdAppointment::getGid, param.getGid());
        List<NdAppointment> list = appointmentService.list(wrapper);
        if (!(list.size() > 0))
            return false;

        return true;
    }


    @Override
    public boolean addNewPrescription(String dids, DrugParam param) {
        NdPrescribe ndPrescribe = new NdPrescribe();
        BeanUtil.copyProperties(param, ndPrescribe);
        ndPrescribe.setDids(dids);
        if (ObjectUtil.isEmpty(param.getId())) {
            prescribeService.save(ndPrescribe);
            return true;
        }else{
            ndPrescribe.setId(param.getId());
            prescribeService.updateById(ndPrescribe);
            return false;
        }
    }


    @Override
    public List<DrugVo> checkDrugsByGp(DrugParam param) {
        LambdaQueryWrapper<NdPrescribe> wrapper = Wrappers.<NdPrescribe>lambdaQuery();
        wrapper.eq(NdPrescribe::getPid, param.getPid()).eq(NdPrescribe::getGid, param.getGid());
        LambdaQueryWrapper<NdDrug> wrapper2 = Wrappers.<NdDrug>lambdaQuery();
        List<NdPrescribe> list = prescribeService.list();
        List<DrugVo> drugList = list.stream().map(s -> {
            DrugVo drugVo = new DrugVo();
            BeanUtil.copyProperties(s, drugVo);
            String[] split = s.getDids().split(",");
            List<NdDrug> ndDrugList = new ArrayList<>();
            for (String s1 : split) {
                NdDrug ndDrug = this.getById(s1);
                ndDrugList.add(ndDrug);
            }
            drugVo.setNdDrugList(ndDrugList);
            return drugVo;
        }).collect(Collectors.toList());

        return drugList;
    }

    @Override
    public boolean removePrescription(Long id) {
        if (prescribeService.removeById(id))
            return true;

        return false;
    }


}
