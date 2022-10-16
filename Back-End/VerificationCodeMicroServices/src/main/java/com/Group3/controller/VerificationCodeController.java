package com.Group3.controller;

import com.Group3.service.VerifyCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/verify")
public class VerificationCodeController {
    @Autowired
    private VerifyCodeService verifyCodeService;

    @GetMapping("/getImage/{email}")
    public HttpEntity image(@PathVariable String email) throws IOException {
        return verifyCodeService.verifyImage(email);
    }
}
