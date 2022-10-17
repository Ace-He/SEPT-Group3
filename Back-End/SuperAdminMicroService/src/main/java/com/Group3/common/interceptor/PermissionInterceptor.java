package com.Group3.common.interceptor;

import com.Group3.common.api.ApiCode;
import com.Group3.common.bean.LocalUser;
import com.Group3.common.exception.UnAuthenticatedException;
import com.Group3.common.util.JwtToken;
import com.Group3.common.util.RedisUtils;
import com.Group3.entity.NdUser;
import com.Group3.param.UserParam;
import com.Group3.service.UserService;
import com.Group3.service.impl.AuthService;
import com.Group3.vo.UserVo;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;


/**
 * Permission interceptor
 */
@Slf4j
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisUtils redisUtils;

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("------------------------->Interceptor, Please login first！");
        Optional<AuthCheck> authCheck = this.getAuthCheck(handler);
        if (!authCheck.isPresent()) {
            return true;
        }

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(bearerToken)) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }

        if (!bearerToken.startsWith("Bearer")) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }

        String[] tokens = bearerToken.split(" ");
        if (!(tokens.length == 2)) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }

        String token = tokens[1];

        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);//Get current user information
        //If optional is not empty, the object in optional is returned
        Map<String, Claim> map = optionalMap
                .orElseThrow(() -> new UnAuthenticatedException(ApiCode.UNAUTHORIZED));

        String uName = map.get("uName").asString();

        //Check whether the token has expired
        if (redisUtils.get("app-online-token:" + uName + ":" + token) == null) {
            throw new UnAuthenticatedException(ApiCode.UNAUTHORIZED);
        }

        //To obtain permission
        boolean valid = this.hasPermission(authCheck.get(), map);

        if (valid) {
            //Save the value for the global user
            this.setToThreadLocal(map);
        }
        return valid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalUser.clear();
        super.afterCompletion(request, response, handler, ex);
    }

    //Determine if the @AuthCheck annotation is used
    private Optional<AuthCheck> getAuthCheck(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AuthCheck authCheck = handlerMethod.getMethod().getAnnotation(AuthCheck.class);
            if (authCheck == null) {
                return Optional.empty();
            }
            return Optional.of(authCheck);
        }
        return Optional.empty();
    }

    private void setToThreadLocal(Map<String, Claim> map) {
        Integer uid = map.get("uid").asInt();
        Integer scope = map.get("scope").asInt();
        NdUser user = userService.getById(uid);
        UserParam userParam = new UserParam();
        userParam.setUserType(user.getUserType());
        UserVo userInfo = authService.getUserInfo(userParam, user);
        if (user == null) {
            throw new UnAuthenticatedException(ApiCode.NOT_PERMISSION);
        }
        LocalUser.set(userInfo, scope);
    }

    private boolean hasPermission(AuthCheck authCheck, Map<String, Claim> map) {
        Integer level = authCheck.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new UnAuthenticatedException(ApiCode.NOT_PERMISSION);
        }
        return true;
    }

}
