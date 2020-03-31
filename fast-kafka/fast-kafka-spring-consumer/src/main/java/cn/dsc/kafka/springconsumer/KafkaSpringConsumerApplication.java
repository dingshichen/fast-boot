package cn.dsc.kafka.springconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用springboot kafka时，不需要再手动开启@EnableKafka
 *
 * @author dingShiChen
 * @since 2019/6/30
 */
@SpringBootApplication
public class KafkaSpringConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringConsumerApplication.class, args);
	}
}
