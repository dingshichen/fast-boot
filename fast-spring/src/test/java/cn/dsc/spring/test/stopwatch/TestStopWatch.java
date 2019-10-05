package cn.dsc.spring.test.stopwatch;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * StopWatch 计时器工具使用，可以装配后用aop统计特定任务的耗时
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
public class TestStopWatch {

	@Test
	public void test() throws InterruptedException {
		log.info("TestStopWatch test run ...");
		StopWatch stopWatch = new StopWatch("统一任务组耗时");
		stopWatch.start("FastStopWatchRunner-1");
		Thread.sleep(3000L);
		stopWatch.stop();

		stopWatch.start("FastStopWatchRunner-2");
		Thread.sleep(3000L);
		stopWatch.stop();

		stopWatch.start("FastStopWatchRunner-3");
		Thread.sleep(3000L);
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
	}
}
