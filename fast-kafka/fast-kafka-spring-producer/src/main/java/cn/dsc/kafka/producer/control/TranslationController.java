package cn.dsc.kafka.producer.control;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshichen
 */
@RestController
@RequestMapping("/translation")
@RequiredArgsConstructor
public class TranslationController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 编码式发送事务消息，需要在开启事务配置：spring.kafka.producer.transaction-id-prefix
     * 如果没有开启，发送时会报错
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
     * 注解式发送事务消息，也需要在开启事务配置：spring.kafka.producer.transaction-id-prefix
     * 如果没有开启，则事务不生效，消息都会直接发送出去
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
