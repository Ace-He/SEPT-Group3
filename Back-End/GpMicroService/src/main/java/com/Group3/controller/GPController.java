package com.Group3.controller;


import com.Group3.common.api.ApiResult;
import com.Group3.common.bean.LocalUser;


import com.Group3.common.config.RabbitMQConfig;
import com.Group3.common.interceptor.AuthCheck;

import com.Group3.entity.NdGp;
import com.Group3.param.GPQueryParam;
import com.Group3.service.GPService;
import com.Group3.service.RabbitMQService;
import com.Group3.vo.GPVo;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Api(value = "GP MicroService")
@RestController
@RequestMapping("/gp")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC_GP))
@Component
public class GPController {

    private final GPService gpService;
    @Resource
    private RabbitMQService rabbitMQService;

    @ApiOperation("Get all information about GP")
    @GetMapping("/listGp")
    public ApiResult listGp(GPQueryParam param) {
        List<GPVo> list = gpService.listGp(param);
        return ApiResult.ok(list);
    }


    /**y
     * @param flag 0 is available，1 is busy
     * @return ApiResult
     */
    @ApiOperation("Modify the doctor's working status")
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
            return ApiResult.error("Only doctors can modify their status");
        return ApiResult.error("Parameter error");
    }

    //The GP's message sending interface
    @PostMapping("/sendMsg")
    public String GpSendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsg(msg);
    }

    //The message receiving interface for the GP
    @RabbitHandler
    public void process(Map map){
        System.out.println("GP："+ map.toString());
    }


}
