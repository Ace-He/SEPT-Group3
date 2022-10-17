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
    @PostMapping("/update/userInfo")
    public ApiResult updateUserInfo() {
        UserVo user = LocalUser.getUser();
        return ApiResult.ok(user);
    }

    @PostMapping("/register")
    public ApiResult register(@RequestBody UserRegisterParam userParam) {

//        Object codeObj = redisUtil.get("code_" + userParam.getEmail());
//        if (ObjectUtil.isEmpty(codeObj)) {
//            return ApiResult.error().message("Please get code first");
//        }
//        String code = codeObj.toString();
//        if (!StringUtils.equals(code, userParam.getCode())) {
//            return ApiResult.error().message("Wrong code");
//        }

        if (!registerService.isCorrectVerifyCode(userParam))
            return ApiResult.error("The verification code is wrong!");


//        LambdaQueryWrapper<NdUser> wrapper = new LambdaQueryWrapper<NdUser>().eq(NdUser::getEmail, userParam.getEmail());
//        List<NdUser> list = userService.list(wrapper);
//        if (list.size() > 0) {
//            return ApiResult.error("The user is already exist");
//        }

        if(!registerService.isValidAccount(userParam))
            return ApiResult.error("This user account already exist");


//        NdUser ndUser = new NdUser();
//        BeanUtil.copyProperties(userParam, ndUser);
//        ndUser.setPassword(SecureUtil.md5(ndUser.getPassword()));
//        userService.save(ndUser);
//
//        if (userParam.getUserType() == 2) {
//            NdPatient ndPatient = new NdPatient();
//            ndPatient.setUid(ndUser.getUid());
//            patientService.save(ndPatient);
//        } else if (userParam.getUserType() == 1) {
//            NdGp ndGp = new NdGp();
//            ndGp.setUid(ndUser.getUid());
//            gpService.save(ndGp);
//        }
        registerService.register(userParam);
        return ApiResult.ok("Register successfully");
    }
}
