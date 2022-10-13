package com.Group3.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser implements Serializable {
    @ApiModelProperty("用户名")
    private String userName;
    private String ip;
    private String address;
    private String key;
    private Date loginTime;
}