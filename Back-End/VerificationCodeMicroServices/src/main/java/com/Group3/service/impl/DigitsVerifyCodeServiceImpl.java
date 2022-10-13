package com.Group3.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.Group3.common.api.ApiCode;
import com.Group3.common.api.ApiResult;
import com.Group3.common.constant.Constants;
import com.Group3.common.exception.BusinessException;
import com.Group3.common.util.RedisUtils;
import com.Group3.common.util.VerifyCodeUtil;
import com.Group3.service.GenerateImageService;
import com.Group3.service.VerifyCodeService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class DigitsVerifyCodeServiceImpl implements VerifyCodeService {

    private static final String IMAGE_FORMAT = "png";
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private GenerateImageService generateImageService;

    @Autowired
    private VerifyCodeUtil verifyCodeUtil;

    public static InputStreamResource imageToInputStreamResource(Image image, String format) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, format, byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return new InputStreamResource(byteArrayInputStream);
    }

    private static String randomDigitString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    @Override
    public ApiResult verify(String username, String code) {
        Object codeObj = redisUtils.get("code_" + username);
        if (ObjectUtil.isEmpty(codeObj)){
            throw new BusinessException(ApiCode.FAIL.getCode(), "未输入验证码!");
        }
        Optional<String> optional = Optional.of(codeObj.toString());
        if (optional.isPresent()) {
            if (codeObj.toString().equals(code)) {
                return null;
            } else {
                throw new BusinessException(ApiCode.FAIL.getCode(), "验证码错误!");
            }
        }
        throw new BusinessException(ApiCode.FAIL.getCode(), "验证码失效!");
    }

    @Override
    public HttpEntity verifyImage(String username) throws IOException {
        String codeKey = "code_" + username;
        redisUtils.del(codeKey);
        if (StringUtils.isEmpty(username)){
            throw new BusinessException(ApiCode.FAIL.getCode() ,"请先输入邮箱!");
        }
        String verifyCode = randomDigitString(verifyCodeUtil.getLen());
        System.out.println(verifyCode);
        Image image = generateImageService.imageWithDisturb(verifyCode);
        InputStreamResource inputStreamResource = imageToInputStreamResource(image, IMAGE_FORMAT);
        try{
            redisUtils.set(codeKey, verifyCode, Constants.VERIFY_CODE_OUT_TIME);
        }catch (Exception e){
            throw new BusinessException(ApiCode.FAIL.getCode() ,"获取验证码失败!");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Pragma", "No-cache");
        httpHeaders.set("Cache-Control", "no-cache");
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .contentType(MediaType.IMAGE_PNG)
                .body(inputStreamResource);
    }
}