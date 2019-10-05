package cn.dsc.security.common;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author dingShiChen
 * @since 2019/6/30
 */
@Data
public class ResponseData<T> {

	private int code;
	private String msg;
	private T data;

	public ResponseData(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public ResponseData(ResponseEnum responseEnum){
		this.code = responseEnum.getCode();
		this.msg = responseEnum.getMsg();
	}

	public static ResponseData success(){
		return new ResponseData(ResponseEnum.SUCCESS);
	}

	@SuppressWarnings("unchecked")
	public static ResponseData success(Object data){
		ResponseData success = success();
		success.setData(data);
		return success;
	}

	public static ResponseData error(){
		return new ResponseData(ResponseEnum.SYSTEM_EXCEPTION);
	}

	public static ResponseData error(ResponseEnum responseEnum){
		return new ResponseData(responseEnum);
	}

	public static ResponseData error(int code, String msg){
		return new ResponseData(code, msg);
	}

	public static ResponseData error(String msg){
		return error(-1, msg);
	}

	public String toJsonString(){
		return JSON.toJSONString(this);
	}
}
