package cn.dsc.security.config;

import cn.dsc.security.common.ResponseData;
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

	private static final String USERNAME_NOT_FOUND = "用户名或密码输入错误，登录失败!";
	private static final String DISABLED_EXCEPTION = "账户被禁用，登录失败，请联系管理员!";
	private static final String NORMAL_ERROR = "登录失败!";

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ResponseData responseData = null;
		//todo 异常捕获不够
		if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
			responseData = ResponseData.error(USERNAME_NOT_FOUND);
		} else if (e instanceof DisabledException) {
			responseData = ResponseData.error(DISABLED_EXCEPTION);
		} else {
			responseData = ResponseData.error(NORMAL_ERROR);
		}
		out.write(responseData.toJsonString());
		out.flush();
		out.close();
	}
}
