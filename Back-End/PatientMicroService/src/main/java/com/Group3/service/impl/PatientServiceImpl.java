package com.Group3.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.entity.NdPatient;
import com.Group3.mapper.PatientMapper;
import com.Group3.param.PatientParam;
import com.Group3.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, NdPatient> implements PatientService {
    @Override
    public boolean editInformation(Integer flag, PatientParam patient) {
        NdPatient ndPatient = new NdPatient();
        BeanUtil.copyProperties(patient, ndPatient);

        if (flag != 1 && flag != 0)
            return false;
        if ( (flag == 1 && updateById(ndPatient)) || (flag == 0 && save(ndPatient)) )    // flag:  0：add，1：modify
            return true;

        return false;
    }

    @Override
    public boolean editStatus(PatientParam patient) {
        NdPatient ndPatient = new NdPatient();
        BeanUtil.copyProperties(patient, ndPatient);
        if (!updateById(ndPatient))
            return false;

        return true;
    }
}
