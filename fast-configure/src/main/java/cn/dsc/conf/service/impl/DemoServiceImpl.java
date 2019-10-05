package cn.dsc.conf.service.impl;

import cn.dsc.conf.service.DemoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
public class DemoServiceImpl implements DemoService {

	@Override
	public void start() {
		log.info("DemoServiceImpl start ...");
	}

	@Override
	public void stop() {
		log.info("DemoServiceImpl stop ...");
	}
}
