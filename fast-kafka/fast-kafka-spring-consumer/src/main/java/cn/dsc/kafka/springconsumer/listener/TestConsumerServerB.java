package cn.dsc.kafka.springconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author dingShiChen
 * @since 2019/6/29
 */
@Slf4j
@Component
public class TestConsumerServerB {

//	@KafkaListener(topics = FastTopic.TEST, groupId = FastGroup.GROUP_B)
//	public void consume(ConsumerRecord<?, ?> record) {
//		log.info("TestConsumerServerB topic : {}, group : b, key : {}, partition : {}, value : {}", record.topic(), record.key(), record.partition(), record.value());
//	}
}
