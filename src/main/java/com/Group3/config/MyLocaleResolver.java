package com.Group3.config;


import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    //i18n
    @Override
    public Locale resolveLocale(HttpServletRequest request){
       String language = request.getParameter("l");

       Locale locale = Locale.getDefault(); //if nothing passed, just using default

        if(!StringUtils.isEmpty(language)){

            String[] splitString = language.split("_");

            locale = new Locale(splitString[0],splitString[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
