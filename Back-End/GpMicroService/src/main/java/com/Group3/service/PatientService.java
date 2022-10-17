package com.Group3.service;

import com.Group3.entity.NdPatient;
import com.Group3.vo.PatientVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PatientService extends IService<NdPatient> {
    List<PatientVo> listPatient(Long pid);
}
