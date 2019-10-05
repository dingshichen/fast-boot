package cn.dsc.conf.service.impl;

import cn.dsc.conf.service.GuideService;
import cn.dsc.conf.service.StoreService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
public class StoreServiceImpl implements StoreService {

	private GuideService guideService;

	public StoreServiceImpl(GuideService guideService) {
		this.guideService = guideService;
	}

	@Override
	public void start() {
		log.info("StoreServiceImpl 的成员变量 guideService 的内存地址：{}", guideService.toString());
	}
}
