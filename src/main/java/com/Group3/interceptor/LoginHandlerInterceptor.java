package com.Group3.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Login interceptor
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute("loginUser");

        if (loginUser == null){ // not login
            request.setAttribute("msg", "Please login first!");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{  // login successfully
            return true;
        }
    }


}
