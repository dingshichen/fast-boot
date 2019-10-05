package cn.dsc.sleuth.model;

import lombok.Data;

/**
 * @author dingShiChen
 * @since 2019/8/28
 */
@Data
public class ResponseData<T> {

	private Integer code;
	private String message;
	private T data;


}
