package cn.dsc.security.config;

import cn.dsc.security.common.ResponseData;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拒绝访问的处理
 * @author dingShiChen
 * @since 2019/7/15
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		httpServletResponse.setCharacterEncoding("UTF-8");
		PrintWriter out = httpServletResponse.getWriter();
		ResponseData responseData = ResponseData.error("权限不足，请联系管理员!");
		out.write(responseData.toJsonString());
		out.flush();
		out.close();
	}
}
