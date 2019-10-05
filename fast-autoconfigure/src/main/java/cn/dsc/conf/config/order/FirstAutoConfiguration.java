package cn.dsc.conf.config.order;

import cn.dsc.conf.service.DemoService;
import cn.dsc.conf.service.impl.DemoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AutoConfigureAfter 和 AutoConfigureBefore 是排序配置，spring只会对classpath:spring.factories里的配置类进行排序
 * @author dingShiChen
 * @since 2019/8/30
 */
@Slf4j
@Configuration
@AutoConfigureAfter(ThirdAutoConfiguration.class)
public class FirstAutoConfiguration {

	public FirstAutoConfiguration() {
		log.info("第一个自动配置初始化");
	}

	@Bean(name = "firstDemoService")
	public DemoService demoService(){
		log.info("firstDemoService 开始被装配");
		return new DemoServiceImpl();
	}
}
