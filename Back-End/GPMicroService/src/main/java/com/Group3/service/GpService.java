package com.Group3.service;


import com.Group3.entity.NdGp;
import com.Group3.param.GpQueryParam;
import com.Group3.vo.GPVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface GpService extends IService<NdGp> {
    List<GPVo> listGp(GpQueryParam param);
    boolean isGpLogin();
    boolean editAvailability(int flag);
}
