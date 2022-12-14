package com.Group3.service.impl;


import com.Group3.common.config.RabbitMQConfig;
import com.Group3.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public String sendMsg(String msg) throws Exception {
        try {
            String msgId = UUID.randomUUID().toString().replace("-","").substring(0,32);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("message id： ", msgId);
            map.put("send time： ", sendTime);
            map.put("message: ", msg);
            rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING, map);
            return "Successfully sent! \nPatient: " + map.toString();
        }catch (Exception e){
            e.printStackTrace();
            e.toString();
            return "Send failure!";
        }

    }
}
