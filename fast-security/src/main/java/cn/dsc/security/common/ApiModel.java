package cn.dsc.security.common;

import lombok.*;

/**
 * @author dingShiChen
 * @since 2019/6/30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiModel<T> {

	private int code;
	private String message;
	private T data;

	public static <T> ApiModel<T> success(){
		return build(0, "success", null);
	}

	public static <T> ApiModel<T> success(T t){
		return build(0, "success", t);
	}

	public static <T> ApiModel<T> fail(String message){
		return build(-1, message, null);
	}

	public static <T> ApiModel<T> build(int code, String message, T t){
		return new ApiModel<>(code, message, t);
	}
}
