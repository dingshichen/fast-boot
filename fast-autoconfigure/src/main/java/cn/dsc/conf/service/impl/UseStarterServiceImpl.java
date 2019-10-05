package cn.dsc.conf.service.impl;

import cn.dsc.conf.service.UseStarterService;
import cn.dsc.faststarter.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dingShiChen
 * @since 2019/8/30
 */
@Slf4j
@Service
public class UseStarterServiceImpl implements UseStarterService {

	@Autowired
	private WordService wordService;

	@Override
	public void echo() {
		log.info("fast-spring-boot-starter 测试结果 : {}", wordService.getMyWord());
	}
}
