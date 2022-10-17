package com.Group3.service;

import com.Group3.entity.NdPatient;
import com.Group3.param.PatientParam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PatientService extends IService<NdPatient> {

    boolean editInformation(Integer flag, PatientParam patient);
    boolean editStatus(PatientParam patient);
}
