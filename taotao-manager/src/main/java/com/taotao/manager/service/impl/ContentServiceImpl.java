package com.taotao.manager.service.impl;

import com.taotao.manager.service.ContentService;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.mapper.TbContentMapper;
import com.taotao.common.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;

@PropertySource(value = {"classpath:resource.properties"})@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @Override
    public TaotaoResult insertContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        try {
//            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
            HttpClientUtil.doGet("http://192.168.178.128:8085/rest/cache/sync/content/"+ content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }
}
