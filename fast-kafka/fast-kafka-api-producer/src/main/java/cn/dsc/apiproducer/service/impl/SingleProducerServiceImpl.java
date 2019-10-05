package cn.dsc.apiproducer.service.impl;

import cn.dsc.apiproducer.MsgVO;
import cn.dsc.apiproducer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 单线程producer,可以保证消息顺序发送
 * producer发送消息，会先写入缓冲区，然后组装一个批次一起发送
 * 1、同步发送，就是等待缓冲区发送到kafka完成，get()方法阻塞线程等待结果，消息不容易丢
 * 2、异步发送，消息写入到缓冲区就立即返回，可以通过回掉方法来获取结果，吞吐量更高
 * @author dingShiChen
 * @since 2019/7/20
 */
@Slf4j
@Primary
@Service
public class SingleProducerServiceImpl implements ProducerService {

	@Autowired
	private KafkaProducer<String, String> producer;

	@Override
	public void sycnSendMessage(MsgVO vo) {
		for (int i = 0; i < vo.getCount(); i++) {
			Future<RecordMetadata> result = producer.send(new ProducerRecord<>(vo.getTopic(), vo.getValue()));
			try {
				log.info("sync send success : {}", result.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error(e.getMessage(), e);
			}

		}
	}

	@Override
	public void AsyncSendMessage(MsgVO vo) {
		for (int i = 0; i < vo.getCount(); i++) {
			producer.send(new ProducerRecord<>(vo.getTopic(), vo.getValue()), new Callback() {
				@Override
				public void onCompletion(RecordMetadata recordMetadata, Exception e) {
					if (e != null) {
						//发送消息异常处理
						log.error(e.getMessage(), e);
					}
					if (recordMetadata != null) {
						//todo
					}
				}
			});
		}
	}


}
