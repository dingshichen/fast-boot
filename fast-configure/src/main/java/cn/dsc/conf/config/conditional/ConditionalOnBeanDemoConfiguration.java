package cn.dsc.conf.config.conditional;

import cn.dsc.conf.service.GuideService;
import cn.dsc.conf.service.MemberService;
import cn.dsc.conf.service.impl.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnBean 条件装配，当条件bean以及在spring容器被定义才装配
 * 注意：在spring ioc的过程中，优先解析@Component，@Service，@Controller注解的类。其次解析配置类，也就是@Configuration标注的类。最后开始解析配置类中定义的bean。
 * 条件注解的解析一定发生在spring ioc的bean definition阶段，因为 spring bean初始化的前提条件就是有对应的bean definition，条件注解正是通过判断bean definition来控制bean能否被解析。所以不一定的先后顺序会造成@ConditionalOnBean未按使用者预期生效
 *
 * 可以换成另外两种解决方式：
 * 1、项目中条件注解依赖的类，大多会交给spring容器管理，所以如果要在配置中Bean通过@ConditionalOnBean依赖配置中的Bean时，完全可以用@ConditionalOnClass(Bean2.class)来代替。
 * 2、如果一定要区分两个配置类的先后顺序，可以将这两个类交与EnableAutoConfiguration管理和触发。也就是定义在META-INF\spring.factories中声明是配置类，然后通过@AutoConfigureBefore、AutoConfigureAfter  AutoConfigureOrder控制先后顺序。之所以这么做是因为这三个注解只对自动配置类的先后顺序生效。
 * 推荐第一种。
 *
 * 与之相反的条件注解是 @ConditionalOnMissingBean
 *
 * @author dingShiChen
 * @since 2019/8/29
 */
@Slf4j
@Configuration
@ConditionalOnBean(GuideService.class)	//这里的GuideService是配置类里定义的，所以这里条件判断的时候没有读取到，所以不生效
public class ConditionalOnBeanDemoConfiguration {

	public ConditionalOnBeanDemoConfiguration() {
		log.info("ConditionalOnBeanDemoConfiguration 条件容器初始化");
	}

	@Bean
	public MemberService memberService(GuideService guideService){
		log.info("ConditionalOnBeanDemoConfiguration 装配条件成立，开始装配 MemberService");
		return new MemberServiceImpl(guideService);
	}
}
