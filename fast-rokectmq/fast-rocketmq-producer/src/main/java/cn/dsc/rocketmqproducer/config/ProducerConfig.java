package cn.dsc.rocketmqproducer.config;

import cn.dsc.rocketmqproducer.listen.FastTransactionListener;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@Configuration
public class ProducerConfig {

	@Bean(initMethod = "start", destroyMethod = "shutdown")
	public DefaultMQProducer defaultMQProducer(ProducerProperties properties) {
		DefaultMQProducer producer = new DefaultMQProducer(properties.getGroupname());
		producer.setNamesrvAddr(properties.getNameserver());
		producer.setRetryTimesWhenSendFailed(properties.getRetrytimes());
		return producer;
	}

	@Bean(initMethod = "start", destroyMethod = "shutdown")
	public TransactionMQProducer transactionMQProducer(ProducerProperties properties, FastTransactionListener fastTransactionListener){
		TransactionMQProducer producer = new TransactionMQProducer(properties.getGroupname());
		producer.setNamesrvAddr(properties.getNameserver());
		producer.setRetryTimesWhenSendFailed(properties.getRetrytimes());
		//指定消息监听对象，用于执行本地事务和消息回查
		producer.setTransactionListener(fastTransactionListener);
		//线程池
		ExecutorService executorService = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000), new ThreadFactory(){
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("client-translaction-msg-check-thread");
				return thread;
			}
		});
		producer.setExecutorService(executorService);
		return producer;
	}
}
