package com.Group3.service.impl;

import com.Group3.entity.NdPatient;
import com.Group3.mapper.PatientMapper;
import com.Group3.service.PatientService;
import com.Group3.vo.PatientVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, NdPatient> implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<PatientVo> listPatient(Long pid) {
        List<PatientVo> list = patientMapper.listPatient(pid);
        return list;
    }
}
