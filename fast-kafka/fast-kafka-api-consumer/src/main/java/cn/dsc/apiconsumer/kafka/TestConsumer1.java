package cn.dsc.apiconsumer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author dingShiChen
 * @since 2019/7/20
 */
@Slf4j
@Component
public class TestConsumer1 implements ApplicationRunner {

	@Autowired
	private KafkaConsumer<String, String> consumer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		try {
//			while (true) {
//				ConsumerRecords<String, String> records = consumer.poll( Duration.ofSeconds(3));
//				for (ConsumerRecord<String, String> record : records) {
//					log.info("consumer record : {}", record);
//				}
//				//异步提交可能会提交失败，所以要注意消费去重
//				consumer.commitAsync((offsets, exception) -> {
//					if (exception != null) {
//						//提交offset失败的处理
//						log.error(exception.getMessage(), exception);
//					} else {
//						log.info("commit success");
//					}
//				});
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		} finally {
//			consumer.close();
//		}
	}
}
