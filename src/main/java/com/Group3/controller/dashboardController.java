package com.Group3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class dashboardController {

    @RequestMapping({"/.dashboard.html"})
    public String dashBoard(){
        return "Ace";
    }
}
