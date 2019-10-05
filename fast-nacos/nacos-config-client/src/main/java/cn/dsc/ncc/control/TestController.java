package cn.dsc.ncc.control;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/8/28
 */
@RestController
public class TestController {

	@NacosValue(value = "${ncc}", autoRefreshed = true)
	private String ncc;

	@GetMapping("/test")
	public String test(){
		return ncc;
	}
}
