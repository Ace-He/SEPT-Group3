package com.Group3.param;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class DrugParam {

    @ApiParam("Patient id")
    private Long pid;

    @ApiParam("GP id")
    private Long gid;

    @ApiParam("The prescription id, if passed, is modified")
    private Long id;


}
