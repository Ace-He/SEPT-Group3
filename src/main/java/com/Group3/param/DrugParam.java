package com.Group3.param;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class DrugParam {

    @ApiParam("患者id")
    private Long pid;

    @ApiParam("医生id")
    private Long gid;

    @ApiParam("开药id，如果传递就说明是修改")
    private Long id;


}
