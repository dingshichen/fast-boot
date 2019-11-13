package cn.dsc.redis.test;

import cn.dsc.redis.SpringRedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author dingShiChen
 * @since 2019/11/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRedisApplication.class)
public class StringValueTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 简单的setGet
	 */
	@Test
	public void setGet(){
		stringRedisTemplate.opsForValue().set("com.bizvane.test", "very", 1L, TimeUnit.MINUTES);
		String value = stringRedisTemplate.opsForValue().get("com.bizvane.test");
		log.info("再取出存入的值：{}", value);
	}

}
