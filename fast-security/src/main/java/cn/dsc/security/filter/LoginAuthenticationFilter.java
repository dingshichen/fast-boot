package cn.dsc.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 自定义登陆拦截器
 * @author dingShiChen
 * @since 2019/7/16
 */
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public LoginAuthenticationFilter() {
		super(new AntPathRequestMatcher("/login", "POST"));
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(getAuthenticationManager(), "authenticationManager must be specified");
		Assert.notNull(getSuccessHandler(), "AuthenticationSuccessHandler must be specified");
		Assert.notNull(getFailureHandler(), "AuthenticationFailureHandler must be specified");
	}

	/**
	 * 从body中获取登陆信息
	 * @param request
	 * @param response
	 * @return
	 * @throws AuthenticationException
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
		String name = null;
		String pwd = null;
		if(StringUtils.hasText(body)) {
			JSONObject jsonObj = JSON.parseObject(body);
			name = jsonObj.getString("name");
			pwd = jsonObj.getString("pwd");
		}
		name = name == null ? "" : name;
		pwd = pwd == null ? "" : pwd;
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name, pwd);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
