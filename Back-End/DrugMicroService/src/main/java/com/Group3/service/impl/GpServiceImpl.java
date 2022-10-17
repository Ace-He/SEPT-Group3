package com.Group3.service.impl;

import com.Group3.entity.NdGp;
import com.Group3.mapper.GpMapper;
import com.Group3.param.GpQueryParam;
import com.Group3.service.GpService;
import com.Group3.vo.GpVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpServiceImpl extends ServiceImpl<GpMapper, NdGp> implements GpService {
    @Autowired
    private GpMapper gpMapper;

    @Override
    public List<GpVo> listGp(GpQueryParam param) {
        List<GpVo> list = gpMapper.listGp(param);
        return list;
    }

}
