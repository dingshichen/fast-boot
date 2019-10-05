package cn.dsc.apiproducer.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author dingShiChen
 * @since 2019/7/20
 */
@Configuration
public class KafkaProducerConfig {

	private static final String KAFKA_PRODUCER = "kafkaProducer";

	@Bean(KAFKA_PRODUCER)
	@ConfigurationProperties(prefix = "kafka.producer")
	public Properties properties(){
		return new Properties();
	}

	@Bean(destroyMethod = "close")
	public KafkaProducer<String, String> kafkaProducer(@Qualifier(KAFKA_PRODUCER) Properties properties){
		return new KafkaProducer<>(properties);
	}
}
