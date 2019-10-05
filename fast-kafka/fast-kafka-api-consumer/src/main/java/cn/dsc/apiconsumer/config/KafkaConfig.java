package cn.dsc.apiconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author dingShiChen
 * @since 2019/7/20
 */
@Configuration
public class KafkaConfig {

	private static final String KAFKA_CONSUMER = "kafkaConsumerProperties";
	private static final String TOPICS = "subscribeTopics";

	@Bean(KAFKA_CONSUMER)
	@ConfigurationProperties(prefix = "kafka.consumer")
	public Properties properties(){
		return new Properties();
	}

	@Bean(TOPICS)
	@ConfigurationProperties(prefix = "kafka.topics.topic")
	public List<String> topics(){
		return new ArrayList<>();
	}

	@Bean(destroyMethod = "close")
	public KafkaConsumer<String, String> kafkaConsumer(@Qualifier(KAFKA_CONSUMER) Properties properties,@Qualifier(TOPICS) List<String> list){
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		//订阅topic，注册一个平衡时回调监听器
		consumer.subscribe(list, new ConsumerRebalanceListener(){

			/**
			 * 消费者平衡操作之前、消费者停止拉取之后被回调
			 * @param collection
			 */
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
				//提交偏移量避免重复消费
				consumer.commitAsync();
			}

			/**
			 *	平衡之后，消费者拉取消息之前被回调
			 * @param collection
			 */
			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				long committedOffset = -1;
				for (TopicPartition partition : collection) {
					//获取该分区已消费的偏移量
					committedOffset = consumer.committed(partition).offset();
					//重置偏移量到上一次提交的偏移量的下一个
					consumer.seek(partition, committedOffset + 1);
				}
			}
		});
		return consumer;
	}
}
