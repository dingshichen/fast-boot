package cn.dsc.kafka.producer.config;

import cn.dsc.kafka.producer.listen.TestProducerListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.LoggingProducerListener;

/**
 * 替换了默认监听{@link LoggingProducerListener}
 * @author dingShiChen
 * @since 2019/7/21
 */
@Configuration
public class ProducerTemplateConfig {

	public ProducerTemplateConfig(KafkaTemplate<String, String> kafkaTemplate){
		kafkaTemplate.setProducerListener(new TestProducerListener());
	}
}
