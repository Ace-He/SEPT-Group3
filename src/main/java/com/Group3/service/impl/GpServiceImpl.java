package com.Group3.service.impl;

import com.Group3.domain.NdGp;
import com.Group3.mapper.GpMapper;
import com.Group3.service.GpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class GpServiceImpl extends ServiceImpl<GpMapper, NdGp> implements GpService {
}
