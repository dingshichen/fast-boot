package cn.dsc.kafka.springconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @KafkaListener 默认的group-id是同一个，所以如果是多个@KafkaListener方法，必须group-id不同
 *
 * @author dingshichen
 */
@Slf4j
@Component
public class DefaultConsumer {

    /**
     * 普通消费
     *
     * @param data
     */
    @KafkaListener(topics = {"test", "test1", "test2"})
    public void onMessage(ConsumerRecord<String, String> data) throws InterruptedException {
        log.info("消费到一条消息 -> 分区 : {}, 内容 : {}", data.partition(), data.value());
        Thread.sleep(1000L);
    }

    /**
     * 从header直接获取消费
     *
     * @param data
     * @param key
     * @param partition
     * @param topic
     * @param ts
     * @throws InterruptedException
     */
//    @KafkaListener(topics = "test2", groupId = "${spring.kafka.consumer.group-id}-test2")
    public void onMessage(@Payload String data,
//                          @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                          @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) throws InterruptedException {
        log.info("消费到一条消息 -> 主题 : {}, 分区 : {}, key : {}, 内容 : {}, ts : {}", topic, partition, "null", data, ts);
        Thread.sleep(1000L);
    }

    /**
     * 批量消费，需要开启 spring.kafka.listener.type=batch
     *
     * @param datas
     */
//    @KafkaListener(topics = "test1", concurrency = "1", groupId = "${spring.kafka.consumer.group-id}-test1")
    public void onMessage(List<String> datas) throws InterruptedException {
        int count = 0;
        for (String data : datas) {
            log.info("批量消费，其中一条：{}", data);
            count ++;
        }
        log.info("全部消费完毕：{}", count);
        Thread.sleep(5000L);
    }
}
