package com.Group3.common.bean;


import com.Group3.common.api.ApiCode;
import com.Group3.common.exception.UnAuthenticatedException;
import com.Group3.common.util.JwtToken;
import com.Group3.common.util.RequestUtils;
import com.Group3.common.util.StringUtils;
import com.Group3.vo.UserVo;
import com.auth0.jwt.interfaces.Claim;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LocalUser {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(UserVo user, Integer scope) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("scope", scope);
        LocalUser.threadLocal.set(map);
    }

    public static void clear() {
        LocalUser.threadLocal.remove();
    }

    public static UserVo getUser() {
        Map<String, Object> map = LocalUser.threadLocal.get();
        UserVo user = (UserVo) map.get("user");
        return user;
    }

}
