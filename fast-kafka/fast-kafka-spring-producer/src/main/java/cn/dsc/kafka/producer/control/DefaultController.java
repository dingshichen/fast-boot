package cn.dsc.kafka.producer.control;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * 生产者消息发送
 *
 * @author dingShiChen
 * @since 2019/6/28
 */
@RestController
@RequestMapping("/default")
@RequiredArgsConstructor
public class DefaultController {

	private final KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 直接异步发送
	 *
	 * @param topic
	 * @param data
	 * @return
	 */
	@GetMapping("/single/{topic}/{data}")
	public String single(@PathVariable String topic, @PathVariable String data){
		kafkaTemplate.send(topic, data);
		return "success";
	}


	/**
	 * 异步发送，增加回调
	 *
	 * @param topic
	 * @param data
	 * @return
	 */
	@GetMapping("/callback/{topic}/{data}")
	public String callback(@PathVariable String topic, @PathVariable String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("生产者回调：发送失败，" + ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("生产者回调：发送成功");
				System.out.println(result);
			}
		});

		//另一个 addCallback 方法是支持lambda表达式的，择请选用
		//future.addCallback(result -> System.out.println("生产者回调：发送成功"), result -> System.out.println("生产者回调：发送失败，" + result.getMessage()));
		return "success";
	}


	/**
	 * 阻塞当前线程，同步发送
	 * 如果不使用Transactions，则默认情况下 DefaultKafkaProducerFactory 按照 KafkaProducer javadocs 中的建议创建一个供所有客户端使用的单例生成器。
	 * 如果调用 flush() 模板，则可能导致使用同一生产者的其他线程延迟。从 2.3 版开始，DefaultKafkaProducerFactory 具有新属性 producerPerThread。设置为时 true，工厂将为每个线程创建（并缓存）单独的生产者，以避免此问题。
	 *
	 * @param topic
	 * @param data
	 * @return
	 */
	@GetMapping("/sync/{topic}/{data}")
	public String sync(@PathVariable String topic, @PathVariable String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		try {
			//调用get方法会阻塞线程，一直等待缓冲区的消息发送完成，实现了同步发送
			SendResult<String, String> result = future.get();
			//可设置限时
//			SendResult<String, String> result = future.get(10L, TimeUnit.MILLISECONDS);
			return result.toString();
		} catch (InterruptedException | ExecutionException e) {
			return e.getMessage();
		}
	}


}
