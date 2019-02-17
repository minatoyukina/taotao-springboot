package com.taotao.search.config;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {
    @Bean
    public CloudSolrClient server() {
        String zkhost = "192.168.178.128:2181,192.168.178.128:2182,192.168.178.128:2183";
        String collection = "collection2";
        CloudSolrClient cloudSolrClient = new CloudSolrClient(zkhost);
        cloudSolrClient.setDefaultCollection(collection);
        return cloudSolrClient;
    }
}
