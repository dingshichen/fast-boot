package cn.dsc.springmvc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dingShiChen
 * @since 2019/7/24
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public String handleApiException(ApiException e){
		log.error(e.getMessage(), e);
		return "系统错误";
	}

	@ExceptionHandler(IllegalStateException.class)
	public String handleIllegalStateException(IllegalStateException e){
		log.error(e.getMessage(), e);
		return "系统错误";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException e){
		log.error(e.getMessage(), e);
		return "系统错误";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e){
		log.error(e.getMessage(), e);
		return "系统错误";
	}
}
