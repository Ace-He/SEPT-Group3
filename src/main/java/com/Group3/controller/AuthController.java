package com.Group3.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.bean.LocalUser;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.common.util.JwtToken;
import com.Group3.common.util.RedisUtils;
import com.Group3.common.util.StringUtils;
import com.Group3.entity.NdGp;
import com.Group3.entity.NdPatient;
import com.Group3.entity.NdUser;
import com.Group3.mapper.GPMapper;
import com.Group3.param.UserParam;
import com.Group3.param.UserRegisterParam;
import com.Group3.service.PatientService;
import com.Group3.service.UserService;
import com.Group3.service.impl.AuthService;
import com.Group3.vo.UserVo;
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
import java.util.List;
import java.util.Map;

@Api(value = "认证模块")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final RedisUtils redisUtil;
    private final UserService userService;
    private final GPMapper gpMapper;
    private final PatientService patientService;
    private final AuthService authService;


    @AuthCheck
    @PostMapping("/update/userInfo")
    public ApiResult updateUserInfo() {
        UserVo user = LocalUser.getUser();
        return ApiResult.ok(user);
    }


    @PostMapping("/register")
    public ApiResult register(@RequestBody UserRegisterParam param) {

        Object codeObj = redisUtil.get("code_" + param.getEmail());
        if (ObjectUtil.isEmpty(codeObj)) {
            return ApiResult.error().message("请先获取验证码(Please get code first)");
        }
        String code = codeObj.toString();
        if (!StringUtils.equals(code, param.getCode())) {
            return ApiResult.error().message("验证码错误(Wrong code)");
        }

        LambdaQueryWrapper<NdUser> wrapper = new LambdaQueryWrapper<NdUser>().eq(NdUser::getEmail, param.getEmail());
        List<NdUser> list = userService.list(wrapper);
        if (list.size() > 0) {
            return ApiResult.error("此用户已存在(The user is already exist)");
        }

        NdUser ndUser = new NdUser();
        BeanUtil.copyProperties(param, ndUser);
        ndUser.setPassword(SecureUtil.md5(ndUser.getPassword()));
        userService.save(ndUser);

        if (param.getUserType() == 2) {
            NdPatient ndPatient = new NdPatient();
            ndPatient.setUid(ndUser.getUid());
            patientService.save(ndPatient);
        } else if (param.getUserType() == 1) {
            NdGp ndGp = new NdGp();
            ndGp.setUid(ndUser.getUid());
            gpMapper.insert(ndGp);
        }
        return ApiResult.ok("注册成功(Register successfully)");
    }

    @ApiOperation("login")
    @PostMapping(value = "/login")
    public ApiResult login(@RequestBody @Validated UserParam user, HttpServletRequest request) {
        Object codeObj = redisUtil.get("code_" + user.getEmail());
        if (ObjectUtil.isEmpty(codeObj)) {
            return ApiResult.error().message("请先获取验证码");
        }

        String code = codeObj.toString();
        if (!StringUtils.equals(code, user.getCode())) {
            return ApiResult.error().message("验证码错误");
        }

        LambdaQueryWrapper<NdUser> wrapper = Wrappers.<NdUser>lambdaQuery().eq(NdUser::getEmail, user.getEmail());

        NdUser NdUser = userService.getOne(wrapper, false);

        if (ObjectUtil.isEmpty(NdUser)) {
            return ApiResult.error().message("用户不存在(The account is not exist!)");
        }

        String pwd = SecureUtil.md5(user.getPassword());
        if (!pwd.equals(NdUser.getPassword())) {
            return ApiResult.error().message("密码错误(Wrong password)");
        }

        String token = JwtToken.makeToken(NdUser.getUid(), NdUser.getUserName());
        String expiresTimeStr = JwtToken.getExpireTime(token);//获取过期时间

        // 保存在线信息
        authService.save(NdUser, token, request, user.getRememberMe());
        Object userInfo = authService.getUserInfo(user, NdUser);

        // 返回 token 和用户信息
        Map<String, Object> map = new HashMap<String, Object>(2) {{
            put("token", token);
            put("expires_time", expiresTimeStr);
            put("userinfo", userInfo);
        }};


        return ApiResult.ok().data(map);
    }
}
