package com.Group3.controller;

import cn.hutool.core.util.ObjectUtil;

import com.Group3.common.api.ApiResult;
import com.Group3.common.bean.LocalUser;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdGp;
import com.Group3.param.GPQueryParam;
import com.Group3.service.GPService;
import com.Group3.vo.GPVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "医生模块")
@RestController
@RequestMapping("/gp")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GPController {

    private final GPService gpService;

    @ApiOperation("获取医生全部信息")
    @GetMapping("/listGp")
    public ApiResult listGp(GPQueryParam param) {
        List<GPVo> list = gpService.listGp(param);
        return ApiResult.ok(list);
    }


    /**
     * @param flag 0表示空闲，1表示忙碌
     * @return
     */
    @ApiOperation("修改医生工作状态")
    @AuthCheck
    @PostMapping("/edit/availability/{flag}")
    public ApiResult editFree(@PathVariable int flag) {
        Long uid = LocalUser.getUser().getUid();

        NdGp ndGp = gpService.getOne(Wrappers.<NdGp>lambdaQuery().eq(NdGp::getUid, uid));
        boolean b = ObjectUtil.isNotEmpty(ndGp);
        if ((flag == 0 || flag == 1) && b) {
            LambdaUpdateWrapper<NdGp> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(NdGp::getUid, uid);
            wrapper.set(NdGp::getIsFree, flag);
            gpService.update(wrapper);
            return ApiResult.ok();
        }
        if(!b)
            return ApiResult.error("只有医生自己才能修改自己的状态");
        return ApiResult.error("参数错误");
    }
}
