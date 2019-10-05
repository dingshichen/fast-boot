package cn.dsc.rocketmqproducer.model;

import lombok.Data;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@Data
public class SendMQVO {

	private String topic;
	private Integer count;
	private String text;
	private String tag;
}
