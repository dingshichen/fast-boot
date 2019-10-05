package cn.dsc.conf.service.impl;

import cn.dsc.conf.service.DemoInnerService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
public class DemoInnerServiceImpl implements DemoInnerService {

	@PostConstruct
	@Override
	public void init() {
		log.info("DemoInnerServiceImpl innit success");
	}
}
