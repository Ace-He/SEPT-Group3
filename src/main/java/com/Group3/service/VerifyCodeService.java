package com.Group3.service;


import com.Group3.common.api.ApiResult;
import org.springframework.http.HttpEntity;

import java.io.IOException;

public interface VerifyCodeService {

    ApiResult verify(String username, String code);

    HttpEntity verifyImage(String username) throws IOException;

}
