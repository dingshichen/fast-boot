package cn.dsc.conf.config.configurable;

import cn.dsc.conf.service.GuideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @Configurable 的作用是为非Spring管理的对象注入Spring Bean
 * 这个场景是 Member对象不是Spring管理的，是使用者自己new出来的对象，@Configurable让Spring来配置这些非Spring管理的对象，即为它们注入需要的依赖。
 * 在反序列化的时候，被@Configurable注解的类和使用new创建的时候一样，也会被拦截然后注入属性
 * 开启这个功能还需要开启一些配置
 * 1、spring-core, spring-beans, spring-context, spring-instrument, spring-aspects, aspectjweaver, spring-tx等几个依赖要有，其中spring-tx是可选的，没有的话会输出一些警告信息。
 * 2、@EnableLoadTimeWeaving和@EnableSpringConfigured两个注解必须有，可以注解在任意被@Configuration注解的类上面
 * 3、运行前加上-javaagent:/path/to/spring-instrument.jar这个jvm参数，/path/to/spring-instrument.jar为你的spring-instrumentjar包的路径
 *
 * 参考博客：https://plentymore.github.io/2018/12/11/Spring-Configurable%E5%9F%BA%E6%9C%AC%E7%94%A8%E6%B3%95/
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
@Configurable
public class Member {

	@Autowired
	private GuideService guideService;

	public void printGuide(){
		log.info("Member 的导购是：{}", guideService);
	}
}
