package cn.dsc.springmvc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter是Servlet规范定义的，由Servlet容器支持
 * @author dingShiChen
 * @since 2019/11/2
 */
@Slf4j
public class FirstFilter implements Filter {

	/**
	 * Servlet容器回调此方法完成初始化
	 * @param filterConfig
	 * @throws ServletException
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("FirstFilter 初始化成功");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("FirstFilter 过滤动作开始");
		//执行过滤链的下一个环节
		chain.doFilter(request, response);
		log.info("FirstFilter 过滤动作结束");
	}

	/**
	 * Servlet容器调用此方法停止过滤器的工作，此时必定过滤器没有在执行任务
	 */
	@Override
	public void destroy() {
		log.info("FirstFilter 销毁");
	}
}
