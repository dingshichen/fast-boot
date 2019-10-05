package cn.dsc.rocketmqproducer.control;

import cn.dsc.rocketmqproducer.model.SendMQVO;
import cn.dsc.rocketmqproducer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/7/27
 */
@RestController
public class ProducerController {

	@Autowired
	private ProducerService producerService;

	@GetMapping("/send")
	public String send(SendMQVO vo){
		return producerService.send(vo);
	}
}
