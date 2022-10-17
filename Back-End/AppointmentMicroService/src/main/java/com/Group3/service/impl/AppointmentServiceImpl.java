package com.Group3.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.util.DateUtil;
import com.Group3.entity.NdAppointment;
import com.Group3.mapper.AppointmentMapper;
import com.Group3.param.AppointmentParam;
import com.Group3.service.AppointmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper,NdAppointment> implements AppointmentService {

    @Override
    public boolean isValid(AppointmentParam param){
        if (param.getEndTime().getTime() < param.getStartTime().getTime())
            return false;

        Date appointDate = DateUtil.beforeOneHourToNowDate(param.getStartTime());
        Date currentDate = new Date();
        if (appointDate.getTime() < currentDate.getTime())   // The appointment date was made in the past
            return false;

        return true;
    }

    @Override
    public boolean addAppointment(AppointmentParam param) {
        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(NdAppointment::getPid, param.getPid()).eq(NdAppointment::getGid, param.getGid()).eq(NdAppointment::getObsolete, 0);
        List<NdAppointment> list = this.list(wrapper);
        if (list.size() > 0) {
            //return ApiResult.error("Appointment exist, if you need to change your Appointment, please cancel the previous Appointment");
            return false;
        }

        NdAppointment ndAppointment = new NdAppointment();
        BeanUtil.copyProperties(param, ndAppointment);
        save(ndAppointment);

        return true;
    }

    @Override
    public boolean cancelAppointment(AppointmentParam param) {

        LambdaQueryWrapper<NdAppointment> wrapper = Wrappers.<NdAppointment>lambdaQuery();
        wrapper.eq(NdAppointment::getGid, param.getGid()).eq(NdAppointment::getPid, param.getPid());
        if (!remove(wrapper)) {
            return false;
        }

        return true;
    }


}
