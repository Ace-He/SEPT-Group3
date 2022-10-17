package com.Group3.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class NdDrug {

    @TableId
    private Long did;
    private String name;
    private String effect;
    private Float Price;
    private Long stock;
}
