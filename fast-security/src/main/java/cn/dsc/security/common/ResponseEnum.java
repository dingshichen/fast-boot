package cn.dsc.security.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dingShiChen
 * @since 2019/6/30
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

	SUCCESS(0, "success"),
	PARAM_INVALID(4001, "参数不合法"),
	OPERATION_FAILED(4002, "操作失败"),
	SYSTEM_EXCEPTION(4002, "系统异常");

	private int code;
	private String msg;
}
