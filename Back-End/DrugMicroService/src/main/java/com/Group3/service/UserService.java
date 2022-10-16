package com.Group3.service;

import com.Group3.common.api.ApiResult;
import com.Group3.entity.NdUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

public interface UserService extends IService<NdUser> {

    ApiResult userLabel(Integer userType);
}

