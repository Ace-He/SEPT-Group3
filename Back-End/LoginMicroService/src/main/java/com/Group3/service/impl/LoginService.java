package com.Group3.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.Group3.common.util.JwtToken;
import com.Group3.common.util.RedisUtils;
import com.Group3.common.util.StringUtils;
import com.Group3.entity.NdUser;
import com.Group3.param.UserParam;
import com.Group3.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    RedisUtils redisUtil;
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    private NdUser ndUser;

    public boolean isCorrectVerifyCode(UserParam user){
        Object codeObj = redisUtil.get("code_" + user.getEmail());
        String code = codeObj.toString();
        if (ObjectUtil.isEmpty(codeObj) || !StringUtils.equals(code, user.getCode()))
            return false;

        return true;
    }

    public boolean isValidAccount(UserParam user){
        LambdaQueryWrapper<NdUser> wrapper = Wrappers.<NdUser>lambdaQuery().eq(NdUser::getEmail, user.getEmail());
        ndUser = userService.getOne(wrapper, false);

        if (ObjectUtil.isEmpty(ndUser))
            return false;
        else
            return true;
    }

    public boolean isCorrectPwd(UserParam user){
        String pwd = SecureUtil.md5(user.getPassword());

        if (!pwd.equals(ndUser.getPassword()))
            return false;
        else
            return true;
    }

    public Map<String, Object> login(UserParam user, HttpServletRequest request){
        String token = JwtToken.makeToken(ndUser.getUid(), ndUser.getUserName());
        String expiresTimeStr = JwtToken.getExpireTime(token);  //Get Expiration Time

        // Save online information
        authService.save(ndUser, token, request, user.getRememberMe());
        Object userInfo = authService.getUserInfo(user, ndUser);

        // Return token and user information
        Map<String, Object> map = new HashMap<>(2) {{
            put("token", token);
            put("expires_time", expiresTimeStr);
            put("userinfo", userInfo);
        }};

        return map;
    }


}
