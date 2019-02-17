package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
