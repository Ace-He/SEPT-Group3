package com.Group3.controller;

import com.Group3.common.api.ApiResult;
import com.Group3.common.bean.LocalUser;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.param.UserRegisterParam;
import com.Group3.service.impl.RegisterService;
import com.Group3.vo.UserVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Authentication module")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    @Autowired
    RegisterService registerService;


    @AuthCheck
    @PostMapping("/getUserInfo")
    public ApiResult getUserInfo() {
        UserVo user = LocalUser.getUser();
        return ApiResult.ok(user);
    }

    @PostMapping("/register")
    public ApiResult register(@RequestBody UserRegisterParam userParam) {
        if (!registerService.isCorrectVerifyCode(userParam))
            return ApiResult.error("The verification code is wrong!");

        if(!registerService.isValidAccount(userParam))
            return ApiResult.error("This user account already exist");

        registerService.register(userParam);
        return ApiResult.ok("Register successfully");
    }
}
