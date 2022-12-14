package com.Group3.service;


import com.Group3.entity.NdGp;
import com.Group3.param.GpQueryParam;
import com.Group3.vo.GpVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface GpService extends IService<NdGp> {
    List<GpVo> listGp(GpQueryParam param);
}
