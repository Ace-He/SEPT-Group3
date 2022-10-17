package com.Group3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("nd_gp")
public class NdGp {

    @TableId(type = IdType.AUTO)
    Long gid;
    Long uid;
    int isFree;

}
