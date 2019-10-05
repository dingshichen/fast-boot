package cn.dsc.rocketmqconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@Slf4j
@Configuration
public class ConsumerConfig {


	@Bean(initMethod = "start", destroyMethod = "shutdown")
	public DefaultMQPullConsumer defaultMQPullConsumer(ConsumerProperties properties){
		DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(properties.getConsumerId());
		consumer.setNamesrvAddr(properties.getNameserver());
		consumer.setConsumerPullTimeoutMillis(properties.getConsumeTimeout());
		consumer.setMaxReconsumeTimes(properties.getMaxReconsumeTimes());
		return consumer;
	}

	@Bean(initMethod = "start", destroyMethod = "shutdown")
	public DefaultMQPushConsumer defaultMQPushConsumer(ConsumerProperties properties) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(properties.getConsumerId());
		consumer.setNamesrvAddr(properties.getNameserver());
		consumer.setMaxReconsumeTimes(properties.getMaxReconsumeTimes());
		consumer.subscribe("test", "*");
		consumer.setConsumeThreadMin(properties.getConsumeThreadMin());
		consumer.setConsumeThreadMin(properties.getConsumeThreadMax());
		//普通消息监听，顺序消息监听使用 MessageListenerOrderly
		consumer.setMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					try {
						log.info("DefaultMQPushConsumer consumer message : {}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
					} catch (UnsupportedEncodingException e) {
						log.error(e.getMessage(), e);
						return ConsumeConcurrentlyStatus.RECONSUME_LATER;
					}
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		return consumer;
	}
}
