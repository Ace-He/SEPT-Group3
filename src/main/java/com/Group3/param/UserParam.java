package com.Group3.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserParam {

    @Email(message = "登录邮箱格式不正确")
    private String email;

    @NotEmpty(message = "登录密码不能为空")
    private String password;

    @NotEmpty(message = "登录验证码不能为空")
    private String code;

    @ApiModelProperty("是否7天免密登录,默认2天")
    private Boolean rememberMe=false;

    @ApiModelProperty("用户类型")
    private int userType;


}