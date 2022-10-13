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

@Api(value = "认证模块")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginControlller {

    private final RedisUtils redisUtil;
    private final UserService userService;
    private final AuthService authService;


    @ApiOperation("login")
    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody @Validated UserParam user, HttpServletRequest request) {
        Object codeObj = redisUtil.get("code_" + user.getEmail());
        if (ObjectUtil.isEmpty(codeObj)) {
            return ApiResult.error().message("Please get the verification code first!");
        }

        String code = codeObj.toString();
        if (!StringUtils.equals(code, user.getCode())) {
            return ApiResult.error().message("Verification code error!");
        }

        LambdaQueryWrapper<NdUser> wrapper = Wrappers.<NdUser>lambdaQuery().eq(NdUser::getEmail, user.getEmail());

        NdUser NdUser = userService.getOne(wrapper, false);

        if (ObjectUtil.isEmpty(NdUser)) {
            return ApiResult.error().message("The account is not exist!");
        }

        String pwd = SecureUtil.md5(user.getPassword());
        if (!pwd.equals(NdUser.getPassword())) {
            return ApiResult.error().message("Wrong password!");
        }

        String token = JwtToken.makeToken(NdUser.getUid(), NdUser.getUserName());
        String expiresTimeStr = JwtToken.getExpireTime(token);//Get Expiration Time

        // Save online information
        authService.save(NdUser, token, request, user.getRememberMe());
        Object userInfo = authService.getUserInfo(user, NdUser);

        // Return token and user information
        Map<String, Object> map = new HashMap<String, Object>(2) {{
            put("token", token);
            put("expires_time", expiresTimeStr);
            put("userinfo", userInfo);
        }};


        return ApiResult.ok().data(map);
    }
}
