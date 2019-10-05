package cn.dsc.kafka.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dingShiChen
 * @since 2019/8/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KMessageVO {

	private String topic;
	private String key;
	private String message;
	private Integer count;
}
