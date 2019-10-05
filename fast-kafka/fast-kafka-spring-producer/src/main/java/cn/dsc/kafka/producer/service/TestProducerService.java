package cn.dsc.kafka.producer.service;

import cn.dsc.kafka.producer.model.KMessageVO;

/**
 * @author dingShiChen
 * @since 2019/6/29
 */
public interface TestProducerService {

	String execute(KMessageVO vo);
}
