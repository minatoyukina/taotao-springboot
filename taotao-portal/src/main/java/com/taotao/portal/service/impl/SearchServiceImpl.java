package com.taotao.portal.service.impl;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@PropertySource(value = {"classpath:resource.properties"}, ignoreResourceNotFound = true)
public class SearchServiceImpl implements SearchService {
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        HashMap<String, String> param = new HashMap<>();
        param.put("q", queryString);
        param.put("page", page + "");
        try {
//            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
            String json = HttpClientUtil.doGet("http://localhost:8083/search/query", param);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
            if (taotaoResult.getStatus() == 200) {
                SearchResult result = (SearchResult) taotaoResult.getData();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
