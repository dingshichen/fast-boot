package cn.dsc.redis.test;

import cn.dsc.redis.SpringRedisApplication;
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

	/**
	 * 存入list
	 */
	@Test
	public void set(){
		redisTemplate.opsForList().rightPushAll(KEY, list);
	}

	@Test
	public void getAll(){
		List<String> lists = redisTemplate.opsForList().range(KEY, 0, -1);
		log.info("取出的所有集合缓存：{}", lists);
	}

	@Test
	public void getIndex(){
		List<String> lists = redisTemplate.opsForList().range(KEY, 1, 1);
		log.info("取出的所有集合缓存：{}", lists);
	}

	@Test
	public void clear(){
		while (redisTemplate.opsForList().size(KEY) > 0){
			log.info("rnamelist 在缓存中有值");
			redisTemplate.opsForList().leftPop(KEY);
		}
	}
}
