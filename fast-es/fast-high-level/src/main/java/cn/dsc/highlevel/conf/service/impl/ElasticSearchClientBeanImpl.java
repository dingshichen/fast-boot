package cn.dsc.highlevel.conf.service.impl;

import cn.dsc.highlevel.conf.properties.ElasticSearchClientProperties;
import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author dingShiChen
 * @since 2019/9/23
 */
@Slf4j
public class ElasticSearchClientBeanImpl implements ElasticSearchClientBean {

	private ElasticSearchClientProperties elasticSearchClientProperties;

	private RestHighLevelClient client;

	public ElasticSearchClientBeanImpl(ElasticSearchClientProperties elasticSearchClientProperties) {
		this.elasticSearchClientProperties = elasticSearchClientProperties;
	}

	@Override
	public void init() {
		this.client = new RestHighLevelClient(RestClient.builder(new HttpHost(elasticSearchClientProperties.getHost(), elasticSearchClientProperties.getPort())));
		log.info("RestHighLevelClient build success");
	}

	@Override
	public void close() {
		try {
			this.client.close();
		} catch (IOException e) {
			log.error("RestHighLevelClient close error : " + e.getMessage(), e);
		}
	}

	@Override
	public RestHighLevelClient getClient() {
		return this.client;
	}
}
