package cn.dsc.highlevel.conf;

import cn.dsc.highlevel.conf.properties.ElasticSearchClientProperties;
import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import cn.dsc.highlevel.conf.service.ElasticSearchService;
import cn.dsc.highlevel.conf.service.impl.ElasticSearchClientBeanImpl;
import cn.dsc.highlevel.conf.service.impl.ElasticSearchServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author dingShiChen
 * @since 2019/9/23
 */
@Configuration
@EnableConfigurationProperties(ElasticSearchClientProperties.class)
public class HighLevelClientAutoConfiguration {

	@Bean(initMethod = "init", destroyMethod = "close")
	@ConditionalOnMissingBean
	public ElasticSearchClientBean elasticSearchClientBean(ElasticSearchClientProperties properties){
		Assert.notNull(properties.getHost(), "can not start app because the ${elasticsearch.client.host} is empty");
		Assert.notNull(properties.getPort(), "can not start app because the ${elasticsearch.client.port} is empty");
		return new ElasticSearchClientBeanImpl(properties);
	}

	@Bean
	@ConditionalOnMissingBean
	public ElasticSearchService elasticSearchService(ElasticSearchClientBean elasticSearchClientBean){
		return new ElasticSearchServiceImpl(elasticSearchClientBean);
	}
}
