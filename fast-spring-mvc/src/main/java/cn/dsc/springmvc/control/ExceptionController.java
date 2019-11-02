package cn.dsc.springmvc.control;

import cn.dsc.springmvc.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Slf4j
@RestController
public class ExceptionController {

	@GetMapping("/ex")
	public String doService(){
		log.info("ExceptionController doService ...");
		throw new ApiException("someting is erro");
	}
}
