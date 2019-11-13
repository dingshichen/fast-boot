package cn.dsc.redis.test;

import cn.dsc.redis.SpringRedisApplication;
import cn.dsc.redis.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/11/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRedisApplication.class)
public class ListValueTest {

	/**
	 * 这里不能用Autowired注入，Autowired按类型注入，泛型会有问题。
	 */
	@Resource
	private RedisTemplate<String, String> redisTemplate;

	private static final String KEY = "rnamelist";

	private List<String> list = Arrays.asList("1", "2", "3");

	@Test
	public void setGet(){
		redisTemplate.opsForList().leftPushAll(KEY, list);
		List<String> values = redisTemplate.opsForList().range(KEY, 0L, -1L);
		log.info("取出的List结果为：{}", values);
	}
}
