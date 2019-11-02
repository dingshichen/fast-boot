package cn.dsc.springmvc.interceptor;

import cn.dsc.springmvc.filter.FirstFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 其实以前都是继承WebMvcConfigurerAdapter类 不过springBoot2.0以上 WebMvcConfigurerAdapter 方法过时，有两种替代方案：
 * 1、继承WebMvcConfigurationSupport
 * 2、实现WebMvcConfigurer
 * 但是继承WebMvcConfigurationSupport会让Spring-boot对mvc的自动配置失效。根据项目情况选择。现在大多数项目是前后端分离，并没有对静态资源有自动配置的需求所以继承WebMvcConfigurationSupport也未尝不可。
 * 添加拦截器
 * @author dingShiChen
 * @since 2019/11/2
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

	@Autowired
	private LoggerInterceptor loggerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor);
	}

	/**
	 * 注册过滤器
	 * 也可以在过滤上注解 @WebFilter(urlPatterns = "/*", filterName = "firstFilter")	同时启动类注解 @ServletComponentScan("cn.dsc.springmvc.filter")
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Bean
	public FilterRegistrationBean registFilter(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new FirstFilter());
		registration.addUrlPatterns("/*");
		registration.setName("firstFilter");
		registration.setOrder(0);
		return registration;
	}
}
