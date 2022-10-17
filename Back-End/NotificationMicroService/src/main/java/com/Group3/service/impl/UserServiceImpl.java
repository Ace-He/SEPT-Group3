package com.Group3.service.impl;

import com.Group3.common.api.ApiResult;
import com.Group3.entity.NdUser;
import com.Group3.mapper.UserMapper;
import com.Group3.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, NdUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ApiResult userLabel(Integer userType) {
        return ApiResult.ok(userMapper.userLabel(userType));
    }
}
