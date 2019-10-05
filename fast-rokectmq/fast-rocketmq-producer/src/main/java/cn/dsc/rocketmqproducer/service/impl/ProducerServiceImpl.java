package cn.dsc.rocketmqproducer.service.impl;

import cn.dsc.rocketmqproducer.model.SendMQVO;
import cn.dsc.rocketmqproducer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	private DefaultMQProducer defaultMQProducer;
	@Autowired
	private TransactionMQProducer transactionMQProducer;

	@Override
	public String send(SendMQVO vo) {
		for (int i = 0; i < vo.getCount(); i++) {
			Message message = new Message(vo.getTopic(), vo.getTag(), vo.getText().getBytes());
			try {
				SendResult result = defaultMQProducer.send(message);
				log.info("ProducerServiceImpl send result : {}", result);
			} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
				log.error(e.getMessage(), e);
				return e.getMessage();
			}
		}
		return "success";
	}

	@Override
	public String orderSend(SendMQVO vo) {
		for (int i = 0; i < vo.getCount(); i++) {
			Message message = new Message(vo.getTopic(), vo.getTag(), vo.getText().getBytes());
			try {
				//选择队列指定发送，RocketMQ只能在一个队列保证消息顺序
				SendResult result = defaultMQProducer.send(message, new MessageQueueSelector() {
					@Override
					public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
						int index = (int) arg;
						return mqs.get(index);
					}
				}, 0);
				log.info("ProducerServiceImpl send result : {}", result);
			} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
				log.error(e.getMessage(), e);
				return e.getMessage();
			}
		}
		return "success";
	}

	@Override
	public String translationSend(SendMQVO vo) {
		for (int i = 0; i < vo.getCount(); i++) {
			Message message = new Message(vo.getTopic(), vo.getTag(), vo.getText().getBytes());
			try {
				transactionMQProducer.sendMessageInTransaction(message, "hello-transaction");
			} catch (MQClientException e) {
				log.error(e.getMessage(), e);
				return e.getMessage();
			}
		}
		return "success";
	}
}
