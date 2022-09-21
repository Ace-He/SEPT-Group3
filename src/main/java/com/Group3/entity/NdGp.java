package com.Group3.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("nd_gp")
public class NdGp {

    @TableId
    Long gid;
    Long uid;
    int isFree;

}
