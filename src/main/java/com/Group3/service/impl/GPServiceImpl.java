package com.Group3.service.impl;

import com.Group3.entity.NdGp;
import com.Group3.mapper.GPMapper;
import com.Group3.param.GPQueryParam;
import com.Group3.service.GPService;

import com.Group3.vo.GPVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPServiceImpl extends ServiceImpl<GPMapper, NdGp> implements GPService {
    @Autowired
    private GPMapper gpMapper;

    @Override
    public List<GPVo> listGp(GPQueryParam param) {
        List<GPVo> list = gpMapper.listGp(param);
        return list;
    }

}
