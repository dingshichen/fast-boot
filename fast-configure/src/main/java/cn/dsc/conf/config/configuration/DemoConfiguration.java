package cn.dsc.conf.config.configuration;

import cn.dsc.conf.service.DemoService;
import cn.dsc.conf.service.GuideService;
import cn.dsc.conf.service.StoreService;
import cn.dsc.conf.service.impl.DemoServiceImpl;
import cn.dsc.conf.service.impl.StoreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 的作用是申明一个配置类，相当于spring的xml配置文件。
 * 配置类的内部可包含多个被@Bean注解的类，这些方法会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext进行扫描，构建bean定义，初始化spring容器
 * @Configuration 注解的配置类有如下要求：
 * 1、不可以是final类型；
 * 2、不可以是匿名类；
 * 3、嵌套的configuration必须是静态类。
 *
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
@Configuration
public class DemoConfiguration {


	/*
		可以使用基于 Java 的配置来管理 bean 的生命周期。@Bean 支持两种属性，即 initMethod 和destroyMethod，这些属性可用于定义生命周期方法。在实例化 bean 或即将销毁它时，容器便可调用生命周期方法。生命周期方法也称为回调方法，因为它将由容器调用。
		使用 @Bean 注释注册的 bean 也支持 JSR-250 规定的标准 @PostConstruct 和 @PreDestroy 注释。
	 */
	@Bean(initMethod = "start", destroyMethod = "stop")
	public DemoService demoService(){
		log.info("DemoConfiguration 开始装配 DemoService");
		return new DemoServiceImpl();
	}

	/*
		不同配置类描述的bean，也可以自动注入
		这里的guideService来自DemoOutConfiguration.DemoInnerConfiguration
	 */
	@Bean(initMethod = "start")
	public StoreService storeService(GuideService guideService){
		return new StoreServiceImpl(guideService);
	}
}
