package cn.dsc.security.config;

import cn.dsc.security.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆成功的处理
 * @author dingShiChen
 * @since 2019/7/15
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		//todo: 实现 JWT 或者分布式 session
//		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
		log.info("AuthenticationSuccessHandlerImpl onAuthenticationSuccess authentication : {}", authentication);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(ResponseData.success().toJsonString());
		out.flush();
		out.close();
	}
}
