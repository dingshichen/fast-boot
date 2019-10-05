package cn.dsc.conf.service.impl;

import cn.dsc.conf.service.GuideService;
import cn.dsc.conf.service.StoreService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingShiChen
 * @since 2019/8/30
 */
@Slf4j
public class StoreServiceImpl implements StoreService {

	private GuideService guideService;

	public StoreServiceImpl(GuideService guideService) {
		this.guideService = guideService;
	}

	@Override
	public void printGuide() {
		log.info("StoreServiceImpl 自动装配的导购是：{}", guideService);
	}
}
