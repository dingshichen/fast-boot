package cn.dsc.highlevel.conf.service;

import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author dingShiChen
 * @since 2019/9/23
 */
public interface ElasticSearchClientBean {

	/**
	 * 初始化方法
	 */
	void init();

	/**
	 * 关闭连接
	 */
	void close();

	/**
	 * 获取Client
	 * @return
	 */
	RestHighLevelClient getClient();
}
