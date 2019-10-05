package cn.dsc.rocketmqconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@Slf4j
@Component
public class PullConsumerRunner implements ApplicationRunner {

	@Autowired
	private DefaultMQPullConsumer consumer;

	private static final Map<MessageQueue, Long> OFFSET_TABLE = new HashMap<>();

	/**
	 * todo 需要实现多线程消费
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("test");
		SINGLE_MQ:
		for (MessageQueue mq : mqs) {
			while (true){
				try {
					PullResult result = consumer.pullBlockIfNotFound(mq, "*", getMessageQueueOffset(mq), 32);
					for (MessageExt msg : result.getMsgFoundList()) {
						log.info("consumer pull msg : {}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
					}
					putMessageQueueOffset(mq, result.getNextBeginOffset());
					switch (result.getPullStatus()){
						case FOUND:
							break;
						case NO_MATCHED_MSG:
							break;
						case NO_NEW_MSG:
							break SINGLE_MQ;
						case OFFSET_ILLEGAL:
							break;
						default:
							break;
					}
				} catch (Exception e){
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	private long getMessageQueueOffset(MessageQueue mq){
		Long offset = OFFSET_TABLE.get(mq);
		if (offset != null){
			return offset;
		}
		return 0;
	}

	private void putMessageQueueOffset(MessageQueue mq, long offset){
		OFFSET_TABLE.put(mq, offset);
	}
}
