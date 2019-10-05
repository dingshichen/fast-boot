package cn.dsc.conf.config;

import cn.dsc.conf.service.DemoService;
import cn.dsc.conf.service.impl.DemoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingShiChen
 * @since 2019/8/30
 */
@Slf4j
@Configuration
public class DemoAutoConfiguration {

	public DemoAutoConfiguration() {
		log.info("DemoAutoConfiguration 容器初始化");
	}

	@Bean
	public DemoService demoService(){
		log.info("DemoAutoConfiguration 开始装配 DemoService");
		return new DemoServiceImpl();
	}

//	@Bean
//	public StoreService storeService(GuideService guideService){
//		return new StoreServiceImpl(guideService);
//	}


}
