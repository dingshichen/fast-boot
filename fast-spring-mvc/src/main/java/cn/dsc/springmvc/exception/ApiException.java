package cn.dsc.springmvc.exception;

import lombok.Getter;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Getter
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 3557449932600416086L;

	private int code = -1;

	public ApiException(String message) {
		super(message);
	}

	public ApiException(int code, String message){
		super(message);
		this.code = code;
	}
}
