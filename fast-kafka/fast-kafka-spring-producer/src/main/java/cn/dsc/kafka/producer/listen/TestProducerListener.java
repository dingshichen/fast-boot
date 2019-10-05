package cn.dsc.kafka.producer.listen;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author dingShiChen
 * @since 2019/7/21
 */
@Slf4j
public class TestProducerListener implements ProducerListener<String, String> {


	@Override
	public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
		log.info("消息发送成功");
	}

	@Override
	public void onError(String topic, Integer partition, String key, String value, Exception exception) {
		log.info("消息发送失败");
	}
}
