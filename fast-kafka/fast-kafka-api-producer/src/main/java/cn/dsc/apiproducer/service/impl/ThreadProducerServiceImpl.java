package cn.dsc.apiproducer.service.impl;

import cn.dsc.apiproducer.MsgVO;
import cn.dsc.apiproducer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 多线程producer
 * 保证不了消息发送的顺序
 * @author dingShiChen
 * @since 2019/7/20
 */
@Slf4j
@Service
public class ThreadProducerServiceImpl implements ProducerService {

	private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

	@Autowired
	private KafkaProducer<String, String> producer;

	@Override
	public void sycnSendMessage(MsgVO vo) {
		executor.execute(() -> {
			Future<RecordMetadata> result = producer.send(new ProducerRecord<>(vo.getTopic(), vo.getValue()));
			try {
				log.info("send message result : {}", result.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error(e.getMessage(), e);
			}
		});
	}

	@Override
	public void AsyncSendMessage(MsgVO vo) {
		executor.execute(() -> producer.send(new ProducerRecord<>(vo.getTopic(), vo.getValue()), (recordMetadata, e) -> {
			if (e != null) {
				//发送消息异常处理
				log.error(e.getMessage(), e);
			}
			if (recordMetadata != null) {
				//todo
			}
		}));
	}
}
