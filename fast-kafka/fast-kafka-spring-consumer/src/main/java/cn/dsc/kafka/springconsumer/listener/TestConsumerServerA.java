//package cn.dsc.kafka.springconsumer.listener;
//
//import cn.dsc.kafka.common.constant.*;
//import cn.dsc.kafka.springconsumer.async.ConsumerTask;
//import cn.dsc.kafka.springconsumer.async.ThreadPoolConfiguration;
//import cn.dsc.kafka.springconsumer.ob.TaskOb;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Executor;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @author dingShiChen
// * @since 2019/6/29
// */
//@Slf4j
//@Component
//public class TestConsumerServerA {
//
//	@Autowired
//	private ConsumerTask consumerTask;
//
//	private AtomicInteger num = new AtomicInteger();
//
//	@Autowired
//	private TaskOb taskOb;
//
//	@KafkaListener(topics = FastTopic.TEST, groupId = FastGroup.GROUP_A)
//	public void consume(ConsumerRecord<?, ?> record) throws InterruptedException, ExecutionException {
//		num.getAndIncrement();
//		consumerTask.task(record);
//		if(num.get() == 5){
//			taskOb.check();
//			num = new AtomicInteger();
//		}
//	}
//}
