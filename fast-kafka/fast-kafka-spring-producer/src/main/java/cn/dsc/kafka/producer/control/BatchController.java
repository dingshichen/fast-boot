package cn.dsc.kafka.producer.control;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试使用，批量发送
 *
 * @author dingshichen
 */
@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送不带 key的，支持多线程消费
     *
     * @param topic
     * @param data
     * @param count
     * @return
     */
    @GetMapping("/send/{topic}/{data}/{count}")
    public String send(@PathVariable String topic, @PathVariable String data, @PathVariable int count) {
        for (int i = 0; i < count; i++) {
            kafkaTemplate.send(topic, data);
        }
        return "success";
    }

    /**
     * 发送带key的，会hash到一个分区去
     *
     * @param topic
     * @param key
     * @param data
     * @param count
     * @return
     */
    @GetMapping("/sendWithKey/{topic}/{key}/{data}/{count}")
    public String sendWithKey(@PathVariable String topic, @PathVariable String key,@PathVariable String data, @PathVariable int count){
        for (int i = 0; i < count; i++) {
            kafkaTemplate.send(topic, key, data);
        }
        return "success";
    }

}
