package cn.dsc.retry.control;

import cn.dsc.retry.service.ReTryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/9/20
 */
@RestController
public class RetryController {

	@Autowired
	private ReTryService reTryService;

	@GetMapping("retry")
	public String retry(String text){
		reTryService.execute(text);
		return "success";
	}
}
