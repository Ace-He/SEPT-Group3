package com.Group3.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserParam {

    @Email(message = "The login email is incorrectly formatted")
    private String email;

    @NotEmpty(message = "The login password cannot be empty")
    private String password;

    @NotEmpty(message = "The login verification code cannot be null")
    private String code;

    @ApiModelProperty("Remember me to login")
    private Boolean rememberMe=false;

    @ApiModelProperty("User Type")
    private int userType;


}
