package com.Group3.controller;


import cn.hutool.core.bean.BeanUtil;
import com.Group3.common.api.ApiResult;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdDrug;
import com.Group3.entity.NdGp;
import com.Group3.entity.NdNews;
import com.Group3.entity.NdPrescribe;
import com.Group3.service.DrugService;
import com.Group3.service.GPService;
import com.Group3.service.NewsService;
import com.Group3.service.PrescribeService;
import com.Group3.vo.DrugVo;
import com.Group3.vo.NewsVo;
import com.Group3.vo.NewsVotwo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("Notification module")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/notification")
public class NotificationController {

    private final NewsService newsService;
    private final PrescribeService prescribeService;
    private final GPService gpService;
    private final DrugService drugService;


    /**
     * Delete notification
     * @param nid
     * @return
     */
    @AuthCheck
    @PostMapping("/delById/{id}")
    public ApiResult delById(@PathVariable("id") Long id){
        newsService.removeById(id);
        return ApiResult.ok();
    }



    /**
     * Get Appointment Notification
     *
     * @param pid
     * @return
     */
    @AuthCheck
    @GetMapping("/getAppointment/{pid}")
    public ApiResult byIdPid(@PathVariable("pid") Long pid) {
        LambdaQueryWrapper<NdNews> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(NdNews::getPid, pid).isNotNull(NdNews::getGid);
        List<NdNews> list = newsService.list(wrapper);
        List<NewsVo> collect = list.stream().map(s -> {
            NewsVo newsVo = new NewsVo();
            BeanUtil.copyProperties(s, newsVo);
            NdGp ndGp = gpService.getById(s.getGid());
            newsVo.setNdGp(ndGp);
            return newsVo;
        }).collect(Collectors.toList());
        return ApiResult.ok(collect);
    }


    /**
     * Get drug Notification
     * @param pid
     * @return
     */
    @AuthCheck
    @GetMapping("/getDrug/{pid}")
    public ApiResult getDrug(@PathVariable("pid") Long pid) {
        LambdaQueryWrapper<NdNews> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(NdNews::getPid, pid).isNotNull(NdNews::getPrescribeId);
        List<NdNews> list = newsService.list(wrapper);
        List<NewsVotwo> collect = list.stream().map(s -> {
            NewsVotwo newsVotwo = new NewsVotwo();
            BeanUtil.copyProperties(s, newsVotwo);
            NdPrescribe ndPrescribe = prescribeService.getById(s.getPrescribeId());
            String[] split = ndPrescribe.getDids().split(",");
            DrugVo drugVo = new DrugVo();
            BeanUtil.copyProperties(ndPrescribe, drugVo);
            for (String s1 : split) {
                NdDrug ndDrug = drugService.getById(s1);
                drugVo.getNdDrugList().add(ndDrug);
            }
            return newsVotwo;
        }).collect(Collectors.toList());
        return ApiResult.ok(collect);
    }

}
