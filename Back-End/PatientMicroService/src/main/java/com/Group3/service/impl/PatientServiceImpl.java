package com.Group3.service.impl;

import com.Group3.entity.NdPatient;
import com.Group3.mapper.PatientMapper;
import com.Group3.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, NdPatient> implements PatientService {
}
