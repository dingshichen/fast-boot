package cn.dsc.conf.config.order;

import cn.dsc.conf.service.DemoService;
import cn.dsc.conf.service.impl.DemoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingShiChen
 * @since 2019/8/30
 */
@Slf4j
@Configuration
@AutoConfigureBefore(ThirdAutoConfiguration.class)
public class SecondAutoConfiguration {

	public SecondAutoConfiguration() {
		log.info("第二个自动配置初始化");
	}

	@Bean(name = "secondDemoService")
	public DemoService demoService(){
		log.info("secondDemoService 开始被装配");
		return new DemoServiceImpl();
	}
}
