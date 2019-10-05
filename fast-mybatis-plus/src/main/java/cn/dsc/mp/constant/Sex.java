package cn.dsc.mp.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
@Getter
@AllArgsConstructor
public enum Sex {

	MAN("1", "男"),
	WOMAN("0", "女");

	@EnumValue
	private final String value;
	private String descp;
}
