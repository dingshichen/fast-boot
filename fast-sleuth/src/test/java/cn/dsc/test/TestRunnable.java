package cn.dsc.test;

import brave.ScopedSpan;
import brave.Tracer;
import brave.Tracing;
import cn.dsc.sleuth.SleuthApplcation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingShiChen
 * @since 2019/9/3
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SleuthApplcation.class)
public class TestRunnable {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void test(){
		log.info("application running ...");
		new Thread(() -> {
			log.info("thread .....");
			Tracing tracing = applicationContext.getBean(Tracing.class);
			SpanNamer spanNamer = applicationContext.getBean(SpanNamer.class);
			Tracer tracer = tracing.tracer();
			ScopedSpan span = tracer.startScopedSpan(spanNamer.name(Thread.currentThread(), "async"));
			log.info("span .....");

			span.finish();
			log.info("thread end");
		}).start();

		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		log.info("application runner success");
	}
}
