package com.Group3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Group3.entity.NdNews;
import com.Group3.mapper.NewsMapper;
import com.Group3.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsMapperImpl extends ServiceImpl<NewsMapper, NdNews> implements NewsService {
}
