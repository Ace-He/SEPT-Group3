package com.Group3.service.impl;

import com.Group3.entity.NdPrescribe;
import com.Group3.mapper.PrescribeMapper;
import com.Group3.service.PrescribeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PrescribeServiceImpl extends ServiceImpl<PrescribeMapper, NdPrescribe> implements PrescribeService {
}
