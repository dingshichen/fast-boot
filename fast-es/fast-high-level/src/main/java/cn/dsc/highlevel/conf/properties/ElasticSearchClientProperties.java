package cn.dsc.highlevel.conf.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dingShiChen
 * @since 2019/9/23
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "elasticsearch.client")
public class ElasticSearchClientProperties {

	private String host;
	private Integer port;
}
