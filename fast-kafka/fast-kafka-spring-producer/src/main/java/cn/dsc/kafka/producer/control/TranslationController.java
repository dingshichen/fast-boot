package cn.dsc.kafka.producer.control;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务消息发送
 * 开启 kafka 发送事务消息，都需要在开启事务配置：spring.kafka.producer.transaction-id-prefix
 * 但是如果开启了，由于 springboot 自动装配的 kafkaTemplate 是单例，所有使用 kafkaTemplate 发送消息，都需要开启事务，不然发送会报错
 *
 * @author dingshichen
 */
@RestController
@RequestMapping("/translation")
@RequiredArgsConstructor
public class TranslationController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 编码式发送事务消息
     * 如果没有开启事务配置，发送时会报错
     *
     * @param topic
     * @param data
     * @return
     */
    @GetMapping("/manual/{topic}/{data}/{commit}")
    public String manual(@PathVariable String topic, @PathVariable String data, @PathVariable boolean commit){

        String result = kafkaTemplate.executeInTransaction(operations -> {
            operations.send(topic, data);
            if (!commit) {
                throw new RuntimeException("事务回滚");
            }
            return "发送完成";
        });

        return result;
    }


    /**
     * 注解式发送事务消息
     * 如果没有开启事务配置，则事务不生效，消息都会直接发送出去
     *
     * @param topic
     * @param data
     * @param commit
     * @return
     */
    @Transactional
    @GetMapping("/auto/{topic}/{data}/{commit}")
    public String auto(@PathVariable String topic, @PathVariable String data, @PathVariable boolean commit) {
        kafkaTemplate.send(topic, data);
        if (!commit) {
            throw new RuntimeException("事务回滚");
        }
        return "success";
    }
}
