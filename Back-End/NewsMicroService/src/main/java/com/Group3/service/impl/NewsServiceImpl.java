package com.Group3.service.impl;

import com.Group3.entity.NdNews;
import com.Group3.mapper.NewsMapper;
import com.Group3.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, NdNews> implements NewsService {
}
