package cn.dsc.rocketmqproducer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
public class ProducerProperties {

	private String nameserver;
	private Integer retrytimes;
	private String groupname;
}
