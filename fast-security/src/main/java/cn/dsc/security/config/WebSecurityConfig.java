package cn.dsc.security.config;

import cn.dsc.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * security 配置
 * @author dingShiChen
 * @since 2019/7/11
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

	@Autowired
	private AccessDecisionManagerImpl accessDecisionManager;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/**
	 * 定义认证用户信息获取来源，密码校验规则
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	/**
	 * 在这里配置哪些页面不需要认证
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/portal/*");
	}

	/**
	 * 定义安全策略
	 * @param http
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()	//配置安全策略
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O object) {
						object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
						object.setAccessDecisionManager(accessDecisionManager);
						return object;
					}
				})
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("name")
				.passwordParameter("pwd")
				.permitAll()
				.failureHandler(authenticationFailureHandler)
				.successHandler(authenticationSuccessHandler)
				.and()
				.logout()
				.logoutUrl("/admin/portal/exit")
				.permitAll()	//退出页面所有人可以访问
				.and()
				.csrf()
				.disable()
				.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}
}
