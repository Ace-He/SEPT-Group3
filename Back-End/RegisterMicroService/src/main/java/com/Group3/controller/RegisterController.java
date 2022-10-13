package com.Group3.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.bean.LocalUser;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.common.util.RedisUtils;
import com.Group3.common.util.StringUtils;
import com.Group3.entity.NdGp;
import com.Group3.entity.NdPatient;
import com.Group3.entity.NdUser;
import com.Group3.mapper.GPMapper;
import com.Group3.param.UserRegisterParam;
import com.Group3.service.GPService;
import com.Group3.service.PatientService;
import com.Group3.service.UserService;
import com.Group3.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "Authentication module")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private final RedisUtils redisUtil;
    private final UserService userService;
    private final PatientService patientService;

    private final GPService gpService;

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
            return ApiResult.error().message("Please get code first");
        }
        String code = codeObj.toString();
        if (!StringUtils.equals(code, param.getCode())) {
            return ApiResult.error().message("Wrong code");
        }

        LambdaQueryWrapper<NdUser> wrapper = new LambdaQueryWrapper<NdUser>().eq(NdUser::getEmail, param.getEmail());
        List<NdUser> list = userService.list(wrapper);
        if (list.size() > 0) {
            return ApiResult.error("The user is already exist");
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
            gpService.save(ndGp);
        }
        return ApiResult.ok("Register successfully");
    }
}
