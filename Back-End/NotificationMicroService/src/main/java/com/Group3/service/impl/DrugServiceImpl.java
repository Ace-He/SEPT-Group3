package com.Group3.service.impl;

import com.Group3.entity.NdDrug;
import com.Group3.mapper.DrugMapper;
import com.Group3.service.DrugService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrugServiceImpl extends ServiceImpl<DrugMapper, NdDrug> implements DrugService {
}
