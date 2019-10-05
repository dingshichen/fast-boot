package cn.dsc.conf.config.conditional;

import cn.dsc.conf.service.GuideService;
import cn.dsc.conf.service.MemberService;
import cn.dsc.conf.service.impl.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnClass 条件装配，当前classpath下是否存在指定类
 *
 * 与之相反的条件注解是 @ConditionalOnMissingClass
 *
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
@Configuration
@ConditionalOnClass(GuideService.class)
public class ConditionalOnClassDemoConfiguration {

	public ConditionalOnClassDemoConfiguration() {
		log.info("ConditionalOnClassDemoConfiguration 条件容器初始化");
	}

	@Bean(initMethod = "printGuide")
	public MemberService memberService(GuideService guideService){
		log.info("ConditionalOnClassDemoConfiguration 开始装配 MemberService");
		return new MemberServiceImpl(guideService);
	}
}
