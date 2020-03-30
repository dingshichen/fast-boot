package cn.dsc.kafka.springconsumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author dingshichen
 */
@Component
public class DefaultConsumer {

    /**
     * 普通消费
     *
     * @param data
     */
    @KafkaListener(topics = "test")
    public void single(ConsumerRecord<String, String> data) {
        System.out.println("消费到一条消息 : " + data);
    }
}
