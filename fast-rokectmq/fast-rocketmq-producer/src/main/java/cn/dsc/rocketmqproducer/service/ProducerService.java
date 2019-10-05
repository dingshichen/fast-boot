package cn.dsc.rocketmqproducer.service;

import cn.dsc.rocketmqproducer.model.SendMQVO;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
public interface ProducerService {

	/**
	 * 普通发送
	 * @param vo
	 * @return
	 */
	String send(SendMQVO vo);

	/**
	 * 顺序发送
	 * @param vo
	 * @return
	 */
	String orderSend(SendMQVO vo);

	/**
	 * 发送事务消息
	 * @param vo
	 * @return
	 */
	String translationSend(SendMQVO vo);
}
