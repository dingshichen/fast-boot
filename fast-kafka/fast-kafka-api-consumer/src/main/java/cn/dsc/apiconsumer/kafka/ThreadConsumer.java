package cn.dsc.apiconsumer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * 多线程消费者
 * @author dingShiChen
 * @since 2019/7/20
 */
@Slf4j
@Component
public class ThreadConsumer implements ApplicationRunner {

	@Autowired
	private KafkaConsumer<String, String> consumer;
	@Value("${threadcount}")
	private Integer threadcount;	//有多少消费者线程，就至少得有多少个分区

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < threadcount; i++) {
			new SingleConsumer(consumer).start();
		}
	}

	private class SingleConsumer extends Thread{

		private KafkaConsumer<String, String> consumer;

		public SingleConsumer(KafkaConsumer<String, String> consumer) {
			this.consumer = consumer;
		}

		@Override
		public void run() {
			try {
				while (true){
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3000));
					for (ConsumerRecord<String, String> record : records) {
						log.info("consumer record : {}", record);
					}
					//同步提交
					consumer.commitSync();
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			} finally {
				consumer.close();
			}
		}
	}
}
