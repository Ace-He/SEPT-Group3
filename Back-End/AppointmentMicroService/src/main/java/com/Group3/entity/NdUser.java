package com.Group3.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("nd_user")
public class NdUser {

    @TableId(type = IdType.AUTO)
    private Long uid;
    private String userName;
    private String email;
    private String sex;
    private int age;
    private String password;
    private int userType;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Version
    private int version;
    @TableLogic
    private int isDel;
}
