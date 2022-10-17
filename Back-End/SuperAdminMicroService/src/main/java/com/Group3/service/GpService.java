package com.Group3.service;


import com.Group3.entity.NdGp;
import com.Group3.param.GPQueryParam;
import com.Group3.vo.GPVo;
import com.Group3.vo.PatientVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface GpService extends IService<NdGp> {
    List<GPVo> listGp(GPQueryParam param);
    NdGp getGp(Long pid);
}
