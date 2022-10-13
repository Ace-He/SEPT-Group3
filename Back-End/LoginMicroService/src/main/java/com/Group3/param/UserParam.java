package com.Group3.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class UserParam {
    @Email(message = "Incorrect login email format")
    private String email;

    @NotEmpty(message = "Login password cannot be empty")
    private String password;

    @NotEmpty(message = "Login verification code cannot be empty")
    private String code;

    @ApiModelProperty("Default 2 days with remember me login")
    private Boolean rememberMe=false;

    @ApiModelProperty("User Type, 0 for admin. 1 for GP. 2 for patient")
    private int userType;
}
