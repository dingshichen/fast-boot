package cn.dsc.kafka.producer.service.impl.normal;

import cn.dsc.kafka.producer.model.KMessageVO;
import cn.dsc.kafka.producer.service.TestProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author dingShiChen
 * @since 2019/6/29
 */
@Slf4j
@Service
public class TestProducerServiceImpl implements TestProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public String execute(KMessageVO vo) {
		for (int i = 1; i <= vo.getCount(); i++) {
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(vo.getTopic(), vo.getKey(), vo.getMessage() + i);
			try {
				//调用get方法会阻塞线程，一直等待缓冲区的消息发送完成，实现了同步发送
				//如果不执行这段代码，可以让回掉监听来异步处理消息结果
				log.info("send result : {}", future.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error(e.getMessage(), e);
			}
		}
		return "success";
	}
}
