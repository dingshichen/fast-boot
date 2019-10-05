package cn.dsc.conf.service.impl;

import cn.dsc.conf.service.GuideService;
import cn.dsc.conf.service.MemberService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
public class MemberServiceImpl implements MemberService {

	private GuideService guideService;

	public MemberServiceImpl(GuideService guideService) {
		this.guideService = guideService;
	}

	@Override
	public void printGuide() {
		log.info("Member 的导购是：{}", guideService);
	}
}
