package cn.dsc.sleuth.control;

import brave.Tracer;
import brave.Tracing;
import cn.dsc.sleuth.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.instrument.async.TraceRunnable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/10/26
 */
@Slf4j
@RestController
public class TestController {

	@Autowired
	private Tracing tracing;

	@Autowired
	private Tracer tracer;

	@Autowired
	private TestService testService;

	/**
	 * 以下几种方式都可以获取到 traceIdString 和 spanIdString
	 *
	 * @return
	 */
	@GetMapping("/trace")
	public String trace(){
		log.info("接口入口日志");
		log.info("Tracing tracing current trace context toString : {}", tracing.currentTraceContext().get().toString());
		log.info("Tracing tracing current trace context traceIdString : {}", tracing.currentTraceContext().get().traceIdString());
		log.info("Tracing tracing current trace context spanIdString : {}", tracing.currentTraceContext().get().spanIdString());
		log.info("Tracer tracer toString : {}", tracer.toString());
		log.info("Tracer tracer current span context traceIdString : {}", tracer.currentSpan().context().traceIdString());
		log.info("Tracer tracer current span context spanIdString : {}", tracer.currentSpan().context().spanIdString());
		return "success";
	}

	@GetMapping("/span")
	public String span() {
		log.info("接口入口日志");

		/**
		 * 自定义多线程创建异步任务时，可以使用 TraceRunnable 来接入链路
		 */
		SpanNamer spanNamer = new SpanNamer() {
			@Override
			public String name(Object object, String defaultValue) {
				return "自定义spanName";
			}
		};
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				log.info("异步线程日志");
			}
		};
		Runnable runnable2 = new TraceRunnable(this.tracing, spanNamer, runnable);
		Thread thread = new Thread(runnable2);
		thread.start();

		/**
		 * 使用 @NewSpan 注解方法来创建新的单元
		 */
		testService.dowork();

		testService.execute();

		log.info("接口返回日志");
		return "success";
	}

}
