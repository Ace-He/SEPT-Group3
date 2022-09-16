package com.Group3.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("nd_gp")
public class NdGp {

    Long gid;
    Long uid;
    int isFree;

}
