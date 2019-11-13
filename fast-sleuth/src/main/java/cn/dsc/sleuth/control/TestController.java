package cn.dsc.sleuth.control;

import brave.ScopedSpan;
import brave.Tracer;
import brave.Tracing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanNamer;
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
	private SpanNamer spanNamer;

	private static final String DEFUALT_SPAN_NAME = "async";


	@GetMapping("/test")
	public String test(){
		log.info("TestController test start");
		Tracer tracer = tracing.tracer();
		ScopedSpan span = tracer.startScopedSpan(spanNamer.name(Thread.currentThread(), DEFUALT_SPAN_NAME));
		log.info("TestController test 1");

		span.finish();
		tracing.close();
		log.info("TestController test end");
		return "success";
	}
}
