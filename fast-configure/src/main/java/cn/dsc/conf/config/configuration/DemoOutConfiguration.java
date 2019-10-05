package cn.dsc.conf.config.configuration;

import cn.dsc.conf.service.DemoInnerService;
import cn.dsc.conf.service.GuideService;
import cn.dsc.conf.service.impl.DemoInnerServiceImpl;
import cn.dsc.conf.service.impl.GuideServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过配置类嵌套的配置类，达到组合多个配置类的目的。但注意内部类必须是静态类。
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
@Configuration
public class DemoOutConfiguration {

	public DemoOutConfiguration() {
		log.info("DemoOutConfiguration 容器初始化");
	}


	//总是在Out前先初始化
	@Configuration
	static class DemoInnerConfiguration {

		public DemoInnerConfiguration() {
			log.info("DemoInnerConfiguration 容器初始化");
		}

		@Bean
		public DemoInnerService demoInnerService(){
			return new DemoInnerServiceImpl();
		}

		@Bean
		public GuideService guideService(){
			return new GuideServiceImpl();
		}
	}
}
