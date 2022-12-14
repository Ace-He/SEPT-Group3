package com.Group3.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.Group3.common.util.RedisUtils;
import com.Group3.common.util.StringUtils;
import com.Group3.entity.NdPatient;
import com.Group3.entity.NdUser;
import com.Group3.param.UserParam;
import com.Group3.service.PatientService;
import com.Group3.service.UserService;
import com.Group3.vo.UserVo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName 登陆认证服务类
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    private final RedisUtils redisUtils;
    private static Integer expiredTimeIn;

    private final UserService userService;

    private final PatientService patientService;

    @Value("${nd.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        AuthService.expiredTimeIn = expiredTimeIn;
    }


    public UserVo getUserInfo(UserParam param, NdUser ndUser) {
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(ndUser, userVo);
        if (param.getUserType() == 2) {
            NdPatient ndPatient = patientService.getOne(Wrappers.<NdPatient>lambdaQuery().eq(NdPatient::getUid, userVo.getUid()), false);
            userVo.setUser(ndPatient);
        }else{
            return null;
        }
        return userVo;
    }


    /**
     * 保存在线用户信息
     *
     * @param ndUser
     * @param token
     * @param request
     * @param rememberMe
     */
//    public void save(NdUser ndUser, String token, HttpServletRequest request, Boolean rememberMe) {
//        String name = ndUser.getUserName();
//        String ip = StringUtils.getIp(request);
//        String browser = StringUtils.getBrowser(request);
//        String address = StringUtils.getCityInfo(ip);
//        OnlineUser onlineUser = null;
//        try {
//            onlineUser = new OnlineUser(name, ip, address, EncryptUtils.desEncrypt(token), new Date());
//        } catch (Exception e) {
//            log.error("---------------->保存在线信息出错");
//            e.printStackTrace();
//        }
//        if (rememberMe) {
//            redisUtils.set("app-online-token:" + onlineUser.getUserName() + ":" + token, onlineUser, expiredTimeIn);
//        } else {
//            redisUtils.set("app-online-token:" + onlineUser.getUserName() + ":" + token, onlineUser, Constants.ONE_DAY_SECONDS * 2);
//        }
//    }
}
