package cn.dsc.highlevel.conf.service.impl;

import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import cn.dsc.highlevel.conf.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingShiChen
 * @since 2019/9/23
 */
@Slf4j
public class ElasticSearchServiceImpl implements ElasticSearchService {


	private ElasticSearchClientBean elasticSearchClientBean;

	public ElasticSearchServiceImpl(ElasticSearchClientBean elasticSearchClientBean) {
		this.elasticSearchClientBean = elasticSearchClientBean;
	}


}
