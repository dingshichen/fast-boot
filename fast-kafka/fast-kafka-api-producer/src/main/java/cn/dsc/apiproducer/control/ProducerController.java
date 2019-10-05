package cn.dsc.apiproducer.control;

import cn.dsc.apiproducer.MsgVO;
import cn.dsc.apiproducer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/7/20
 */
@RestController
public class ProducerController {

	@Autowired
	private ProducerService producerService;

	@PostMapping("/send")
	public String send(@RequestBody MsgVO vo){
		producerService.sycnSendMessage(vo);
		return "success";
	}
}
