package com.Group3.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class loginController {

    @RequestMapping("/user/login")

    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session){

        // simulation
        if(!StringUtils.isEmpty(username) && "123456".equals(password))
        {
            session.setAttribute("loginUser", username);
            return "redirect:/dashboard.html";
        }else{
            model.addAttribute("msg","Username or password is incorrect!");
            return "redirect:/index.html";
        }

    }
}
