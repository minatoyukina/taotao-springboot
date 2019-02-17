package com.taotao.search.service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private SearchDao searchDao;

    public SearchResult search(String queryString, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        query.set("df", "item_keywords");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        SearchResult searchResult = searchDao.search(query);
        long recordCount = searchResult.getRecordCount();
        long pageCount = recordCount / rows;
        if (recordCount % rows > 0) {
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(page);
        return searchResult;
    }
}
