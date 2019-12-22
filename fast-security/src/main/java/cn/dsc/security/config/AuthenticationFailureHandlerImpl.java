package cn.dsc.security.config;

import cn.dsc.security.common.ApiModel;
import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆失败的处理
 * @author dingShiChen
 * @since 2019/7/15
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		PrintWriter out = response.getWriter();
		ApiModel<Object> apiModel = null;
		//todo 异常捕获不够
		if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
			apiModel = ApiModel.fail("用户名或密码输入错误，登录失败!");
		} else if (e instanceof DisabledException) {
			apiModel = ApiModel.fail("账户被禁用，登录失败，请联系管理员!");
		} else {
			apiModel = ApiModel.fail("登录失败!");
		}
		out.write(JSON.toJSONString(apiModel));
		out.flush();
		out.close();
	}
}
