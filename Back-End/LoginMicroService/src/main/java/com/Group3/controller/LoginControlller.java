package com.Group3.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.util.JwtToken;
import com.Group3.common.util.RedisUtils;
import com.Group3.common.util.StringUtils;
import com.Group3.entity.NdUser;
import com.Group3.param.UserParam;
import com.Group3.service.UserService;
import com.Group3.service.impl.AuthService;
import com.Group3.service.impl.LoginService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Login MicroService")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginControlller {
    @Autowired
    LoginService loginService;


    @ApiOperation("login")
    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody @Validated UserParam user, HttpServletRequest request) {
        if (!loginService.isCorrectVerifyCode(user))
            return ApiResult.error().message("The verification code is wrong!");

        if (!loginService.isValidAccount(user))
            return ApiResult.error().message("This account doesn't exist!");

        if (!loginService.isCorrectPwd(user))
            return ApiResult.error().message("Wrong password!");

        Map<String, Object> userInformation = loginService.login(user, request);

        return ApiResult.ok().data(userInformation);
    }
}
