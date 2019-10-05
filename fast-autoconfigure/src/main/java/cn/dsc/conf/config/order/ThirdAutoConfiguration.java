package cn.dsc.conf.config.order;

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
public class ThirdAutoConfiguration {


	public ThirdAutoConfiguration() {
		log.info("第三个自动配置初始化");
	}

	@Bean(name = "thirdDemoService")
	public DemoService demoService(){
		log.info("thirdDemoService 开始被装配");
		return new DemoServiceImpl();
	}
}
