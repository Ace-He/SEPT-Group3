package com.Group3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("nd_prescribe")
public class NdPrescribe {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String dids;
    private Long pid;
    private Long gid;
}
