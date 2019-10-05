package cn.dsc.rocketmqconsumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rocketmq.consumer")
public class ConsumerProperties {

	private String nameserver;
	private String ConsumerId;
	private Integer maxReconsumeTimes;
	private Integer consumeTimeout;
	private Integer consumeThreadMin;
	private Integer consumeThreadMax;
}
