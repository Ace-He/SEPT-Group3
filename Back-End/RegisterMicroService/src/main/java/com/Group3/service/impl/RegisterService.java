package com.Group3.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.Group3.common.util.RedisUtils;
import com.Group3.common.util.StringUtils;
import com.Group3.entity.NdGp;
import com.Group3.entity.NdPatient;
import com.Group3.entity.NdUser;
import com.Group3.param.UserParam;
import com.Group3.param.UserRegisterParam;
import com.Group3.service.GPService;
import com.Group3.service.PatientService;
import com.Group3.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserService userService;
    @Autowired
    PatientService patientService;
    @Autowired
    GPService gpService;

    public boolean isCorrectVerifyCode(UserRegisterParam userParam){
        Object codeObj = redisUtils.get("code_" + userParam.getEmail());
        String code = codeObj.toString();
        if (ObjectUtil.isEmpty(codeObj) || !StringUtils.equals(code, userParam.getCode()))
            return false;

        return true;
    }

    public boolean isValidAccount(UserRegisterParam userParam){
        LambdaQueryWrapper<NdUser> wrapper = new LambdaQueryWrapper<NdUser>().eq(NdUser::getEmail, userParam.getEmail());
        List<NdUser> list = userService.list(wrapper);
        if (list.size() > 0)
            return false;

        return true;
    }

    public void register(UserRegisterParam userParam){
        NdUser ndUser = new NdUser();
        BeanUtil.copyProperties(userParam, ndUser);
        ndUser.setPassword(SecureUtil.md5(ndUser.getPassword()));
        userService.save(ndUser);

        if (userParam.getUserType() == 2) {
            NdPatient ndPatient = new NdPatient();
            ndPatient.setUid(ndUser.getUid());
            patientService.save(ndPatient);
        } else if (userParam.getUserType() == 1) {
            NdGp ndGp = new NdGp();
            ndGp.setUid(ndUser.getUid());
            gpService.save(ndGp);
        }
    }

}
