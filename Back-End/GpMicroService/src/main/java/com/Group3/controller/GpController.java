package com.Group3.controller;


import com.Group3.common.api.ApiResult;


import com.Group3.common.config.RabbitMQConfig;
import com.Group3.common.interceptor.AuthCheck;

import com.Group3.param.GpQueryParam;
import com.Group3.service.GpService;
import com.Group3.service.RabbitMQService;
import com.Group3.vo.GPVo;
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
public class GpController {

    private final GpService gpService;
    @Resource
    private RabbitMQService rabbitMQService;

    @AuthCheck
    @ApiOperation("Get all information about GP")
    @GetMapping("/listGp")
    public ApiResult listGp(GpQueryParam param) {
        List<GPVo> list = gpService.listGp(param);
        return ApiResult.ok(list);
    }


    /**y
     * @param flag 0 is available，1 is not available
     * @return ApiResult
     */
    @AuthCheck
    @ApiOperation("Modify the gp's working status")
    @PostMapping("/edit/availability/{flag}")
    public ApiResult editAvailability(@PathVariable int flag) {

        if (!gpService.isGpLogin())
            return ApiResult.error("Only doctors can modify their status");

        if (!gpService.editAvailability(flag)) {
            return ApiResult.error("Parameter flag is error, hint: 0 is available，1 is not available.");
        }else{
            return ApiResult.ok("Edit successfully!");
        }

//        Long uid = LocalUser.getUser().getUid();
//
//        NdGp ndGp = gpService.getOne(Wrappers.<NdGp>lambdaQuery().eq(NdGp::getUid, uid));
//        boolean b = ObjectUtil.isNotEmpty(ndGp);
//        if ((flag == 0 || flag == 1) && b) {
//            LambdaUpdateWrapper<NdGp> wrapper = new LambdaUpdateWrapper<>();
//            wrapper.eq(NdGp::getUid, uid);
//            wrapper.set(NdGp::getIsFree, flag);
//            gpService.update(wrapper);
//            return ApiResult.ok();
//        }
//        if(!b)
//            return ApiResult.error("Only doctors can modify their status");
//        return ApiResult.error("Parameter error");
    }

    //The GP's message sending interface
    @AuthCheck
    @PostMapping("/sendMsg")
    public String GpSendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsg(msg);
    }

    //The message receiving interface for the GP
    @AuthCheck
    @RabbitHandler
    public void process(Map map){
        System.out.println("GP："+ map.toString());
    }


}
