package cn.dsc.apiproducer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dingShiChen
 * @since 2019/7/20
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MsgVO {

	private String topic;
	private String value;
	private Integer count;
}
