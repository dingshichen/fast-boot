package cn.dsc.kafka.producer.control;

import cn.dsc.kafka.producer.model.KMessageVO;
import cn.dsc.kafka.producer.service.TestProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/6/28
 */
@RestController
@RequestMapping("/kafka")
public class ProducerController {

	@Autowired
	private TestProducerService testProducerService;

	@PostMapping("/send")
	public String send(@RequestBody KMessageVO vo){
		return testProducerService.execute(vo);
	}
}
