package com.Group3.common.handler;

import com.Group3.entity.NdNotification;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //The padding strategy for insertion
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill......");
        if (metaObject.getOriginalObject() instanceof NdNotification) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        } else {
            this.setFieldValByName("createTime", new Date(), metaObject);
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }

    }

    //The padding strategy for updates
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill......");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
