package cn.dsc.security.config;

import cn.dsc.security.common.ApiModel;
import com.alibaba.fastjson.JSON;
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
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ApiModel<Object> fail = ApiModel.fail("权限不足，请联系管理员!");
		out.write(JSON.toJSONString(fail));
		out.flush();
		out.close();
	}
}
