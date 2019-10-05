package cn.dsc.apiproducer.service;

import cn.dsc.apiproducer.MsgVO;

/**
 * @author dingShiChen
 * @since 2019/7/20
 */
public interface ProducerService {

	void sycnSendMessage(MsgVO vo);

	void AsyncSendMessage(MsgVO vo);
}
