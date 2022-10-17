package com.Group3.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.Group3.common.bean.LocalUser;
import com.Group3.entity.NdGp;
import com.Group3.mapper.GpMapper;
import com.Group3.param.GpQueryParam;
import com.Group3.service.GpService;
import com.Group3.vo.GPVo;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpServiceImpl extends ServiceImpl<GpMapper, NdGp> implements GpService {
    @Autowired
    private GpMapper gpMapper;

    @Override
    public List<GPVo> listGp(GpQueryParam param) {
        List<GPVo> list = gpMapper.listGp(param);
        return list;
    }


    @Override
    public boolean isGpLogin() {
        Long uid = LocalUser.getUser().getUid();
        NdGp ndGp = this.getOne(Wrappers.<NdGp>lambdaQuery().eq(NdGp::getUid, uid));
        boolean isGpLogin = ObjectUtil.isNotEmpty(ndGp);

        return isGpLogin;
    }

    @Override
    public boolean editAvailability(int flag){    //flag: 0 is available，1 is not available
        Long uid = LocalUser.getUser().getUid();

        if ((flag == 0 || flag == 1) && isGpLogin()) {
            LambdaUpdateWrapper<NdGp> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(NdGp::getUid, uid);
            wrapper.set(NdGp::getIsFree, flag);
            update(wrapper);
            return true;
        }

        return false;
    }


}
